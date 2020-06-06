package com.erdemtsynduev.websocket

import android.util.Log
import io.crossbar.autobahn.websocket.exceptions.WebSocketException
import org.json.JSONException
import org.json.JSONObject
import timber.log.Timber
import java.net.URI
import java.net.URISyntaxException
import java.util.*

/**
 * WebSocket client implementation.
 *
 * <p>All public methods should be called from a looper executor thread
 * passed in a constructor, otherwise exception will be thrown.
 * All events are dispatched on the same thread.
 */
class WebSocketChannelClient(var webSocketChannelEvents: WebSocketChannelEvents) {

    private var events: WebSocketChannelEvents? = webSocketChannelEvents
    private var ws: de.tavendo.autobahn.WebSocketConnection? = null
    private var wsServerUrl: String? = null
    private var postServerUrl: String? = null
    private var roomID: String? = null
    private var clientID: String? = null
    private var state: WebSocketConnectionState? = WebSocketConnectionState.NEW

    // Do not remove this member variable. If this is removed, the observer gets garbage collected and
    // this causes test breakages.
    private var wsObserver: WebSocketChannelClient.WebSocketObserver? =
        null
    private var closeEventLock = Any()
    private var closeEvent = false

    // WebSocket send queue. Messages are added to the queue when WebSocket
    // client is not registered and are consumed in register() call.
    private var wsSendQueue: List<String> = ArrayList()

    fun getState(): WebSocketConnectionState? {
        return state
    }

    suspend fun connect(wsUrl: String, postUrl: String) {
        if (state != WebSocketConnectionState.NEW) {
            Timber.e("WebSocket is already connected.")
            return
        }
        wsServerUrl = wsUrl
        postServerUrl = postUrl
        closeEvent = false
        Timber.d("Connecting WebSocket to: $wsUrl. Post URL: $postUrl")
        ws = de.tavendo.autobahn.WebSocketConnection()
        wsObserver = WebSocketObserver()
        try {
            ws.connect(URI(wsServerUrl), wsObserver)
        } catch (e: URISyntaxException) {
            reportError("URI error: " + e.message)
        } catch (e: WebSocketException) {
            reportError("WebSocket connection error: " + e.message)
        }
    }

    suspend fun disconnect(waitForComplete: Boolean) {
        Timber.d("Disconnect WebSocket. State: $state")
        if (state == WebSocketConnectionState.REGISTERED) {
            // Send "bye" to WebSocket server.
            send("{\"type\": \"bye\"}")
            state = WebSocketConnectionState.CONNECTED
            // Send http DELETE to http WebSocket server.
            sendWSSMessage("DELETE", "")
        }
        // Close WebSocket in CONNECTED or ERROR states only.
        if (state == WebSocketConnectionState.CONNECTED || state == WebSocketConnectionState.ERROR) {
            ws.disconnect()
            state = WebSocketConnectionState.CLOSED

            // Wait for websocket close event to prevent websocket library from
            // sending any pending messages to deleted looper thread.
            if (waitForComplete) {
                synchronized(closeEventLock) {
                    while (!closeEvent) {
                        try {
                            closeEventLock.wait(CLOSE_TIMEOUT.toLong())
                            break
                        } catch (e: InterruptedException) {
                            Timber.e("Wait error: $e")
                        }
                    }
                }
            }
        }
        Timber.d("Disconnecting WebSocket done.")
    }

    suspend fun register(roomID: String, clientID: String) {
        this.roomID = roomID
        this.clientID = clientID
        if (state != WebSocketConnectionState.CONNECTED) {
            Timber.w("WebSocket register() in state $state")
            return
        }
        Timber.d("Registering WebSocket for room $roomID. ClientID: $clientID")
        val json = JSONObject()
        try {
            json.put("cmd", "register")
            json.put("roomid", roomID)
            json.put("clientid", clientID)
            Timber.d("C->WSS: $json")
            ws.sendTextMessage(json.toString())
            state = WebSocketConnectionState.REGISTERED
            // Send any previously accumulated messages.
            for (sendMessage in wsSendQueue) {
                send(sendMessage)
            }
            wsSendQueue.clear()
        } catch (e: JSONException) {
            reportError("WebSocket register JSON error: " + e.message)
        }
    }

    private suspend fun send(message: String) {
        when (state) {
            WebSocketConnectionState.NEW, WebSocketConnectionState.CONNECTED -> {
                // Store outgoing messages and send them after websocket client
                // is registered.
                Timber.d("WS ACC: $message")
                wsSendQueue.add(message)
                return
            }
            WebSocketConnectionState.ERROR, WebSocketConnectionState.CLOSED -> {
                Timber.e("WebSocket send() in error or closed state : $message")
                return
            }
            WebSocketConnectionState.REGISTERED -> {
                val json = JSONObject()
                try {
                    json.put("cmd", "send")
                    json.put("msg", message)
                    val messageJson = json.toString()
                    Timber.d("C->WSS: $messageJson")
                    ws.sendTextMessage(messageJson)
                } catch (e: JSONException) {
                    reportError("WebSocket send JSON error: " + e.message)
                }
            }
        }
    }

    // This call can be used to send WebSocket messages before WebSocket
    // connection is opened.
    fun post(message: String?) {
        sendWSSMessage("POST", message)
    }

    // Asynchronously send POST/DELETE to WebSocket server.
    private fun sendWSSMessage(method: String, message: String) {
        val postUrl = "$postServerUrl/$roomID/$clientID"
        Log.d(
            WebSocketChannelClient.TAG,
            "WS $method : $postUrl : $message"
        )
        val httpConnection =
            AsyncHttpURLConnection(method, postUrl, message, object : AsyncHttpEvents() {
                fun onHttpError(errorMessage: String) {
                    reportError("WS $method error: $errorMessage")
                }

                fun onHttpComplete(response: String) {}
            })
        httpConnection.send()
    }

    private suspend fun reportError(errorMessage: String) {
        Timber.e(errorMessage)
        if (state != WebSocketConnectionState.ERROR) {
            state = WebSocketConnectionState.ERROR
            events!!.onWebSocketError(errorMessage)
        }
    }

    companion object {
        val CLOSE_TIMEOUT = 1000
    }
}