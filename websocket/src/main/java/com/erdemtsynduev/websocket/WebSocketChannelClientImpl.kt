package com.erdemtsynduev.websocket

import io.crossbar.autobahn.websocket.WebSocketConnection
import io.crossbar.autobahn.websocket.WebSocketConnectionHandler
import io.crossbar.autobahn.websocket.exceptions.WebSocketException
import io.crossbar.autobahn.websocket.types.ConnectionResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import org.json.JSONException
import org.json.JSONObject
import timber.log.Timber
import java.net.URISyntaxException

/**
 * WebSocket client implementation.
 *
 * <p>All public methods should be called from a looper executor thread
 * passed in a constructor, otherwise exception will be thrown.
 * All events are dispatched on the same thread.
 */
class WebSocketChannelClientImpl(var webSocketChannelEvents: WebSocketChannelEvents) :
    WebSocketChannelClient, WebSocketConnectionHandler() {

    private var webSocketConnection: WebSocketConnection? = null
    private var webSocketServerUrl: String? = null
    private var postServerUrl: String? = null
    private var roomID: String? = null
    private var clientID: String? = null
    private var connectionState: WebSocketConnectionState? = WebSocketConnectionState.NEW

    @ObsoleteCoroutinesApi
    private val scope = CoroutineScope(newSingleThreadContext("webSocketChannel"))

    // WebSocket send queue. Messages are added to the queue when WebSocket
    // client is not registered and are consumed in register() call.
    private var webSocketSendQueue: ArrayList<String> = ArrayList()

    override fun getConnectionState(): WebSocketConnectionState? {
        return connectionState
    }

    override suspend fun connect(wsUrl: String, postUrl: String) {
        if (connectionState != WebSocketConnectionState.NEW) {
            Timber.e("WebSocket is already connected.")
            return
        }
        webSocketServerUrl = wsUrl
        postServerUrl = postUrl
        Timber.d("Connecting WebSocket to: $wsUrl. Post URL: $postUrl")
        webSocketConnection = WebSocketConnection()
        try {
            webSocketConnection?.connect(webSocketServerUrl, this)
        } catch (e: URISyntaxException) {
            reportError("URI error: " + e.message)
        } catch (e: WebSocketException) {
            reportError("WebSocket connection error: " + e.message)
        }
    }

    override suspend fun disconnect() {
        Timber.d("Disconnect WebSocket. State: $connectionState")
        if (connectionState == WebSocketConnectionState.REGISTERED) {
            // Send "bye" to WebSocket server.
            send("{\"type\": \"bye\"}")
            connectionState = WebSocketConnectionState.CONNECTED
            // Send http DELETE to http WebSocket server.
            sendWSSMessage("DELETE", "")
        }
        // Close WebSocket in CONNECTED or ERROR states only.
        if (connectionState == WebSocketConnectionState.CONNECTED || connectionState == WebSocketConnectionState.ERROR) {
            webSocketConnection?.sendClose()
            connectionState = WebSocketConnectionState.CLOSED
        }
        Timber.d("Disconnecting WebSocket done.")
    }

    override suspend fun register(roomID: String, clientID: String) {
        this.roomID = roomID
        this.clientID = clientID
        if (connectionState != WebSocketConnectionState.CONNECTED) {
            Timber.w("WebSocket register() in state $connectionState")
            return
        }
        Timber.d("Registering WebSocket for room $roomID. ClientID: $clientID")
        val json = JSONObject()
        try {
            json.put("cmd", "register")
            json.put("roomid", roomID)
            json.put("clientid", clientID)
            Timber.d("C->WSS: $json")
            webSocketConnection?.sendMessage(json.toString())
            connectionState = WebSocketConnectionState.REGISTERED
            // Send any previously accumulated messages.
            for (sendMessage in webSocketSendQueue) {
                send(sendMessage)
            }
            webSocketSendQueue.clear()
        } catch (e: JSONException) {
            reportError("WebSocket register JSON error: " + e.message)
        }
    }

    private suspend fun send(message: String) {
        when (connectionState) {
            WebSocketConnectionState.NEW, WebSocketConnectionState.CONNECTED -> {
                // Store outgoing messages and send them after websocket client
                // is registered.
                Timber.d("WS ACC: $message")
                webSocketSendQueue.add(message)
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
                    webSocketConnection?.sendMessage(messageJson)
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
        Timber.d("WS $method : $postUrl : $message")
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
        if (connectionState != WebSocketConnectionState.ERROR) {
            connectionState = WebSocketConnectionState.ERROR
            webSocketChannelEvents.onWebSocketError(errorMessage)
        }
    }

    override fun onConnect(response: ConnectionResponse?) {
        Timber.d("Connected to server")
    }

    @ObsoleteCoroutinesApi
    override fun onOpen() {
        Timber.d("WebSocket connection opened to: $webSocketServerUrl")
        connectionState = WebSocketConnectionState.CONNECTED
        // Check if we have pending register request.
        if (roomID != null && clientID != null) {
            scope.launch {
                register(roomID!!, clientID!!)
            }
        }
    }

    @ObsoleteCoroutinesApi
    override fun onClose(code: Int, reason: String?) {
        Timber.d("WebSocket connection closed. Code: $code. Reason: $reason. State: $connectionState")
        if (connectionState != WebSocketConnectionState.CLOSED) {
            connectionState = WebSocketConnectionState.CLOSED
            scope.launch {
                webSocketChannelEvents.onWebSocketClose()
            }
        }
    }

    @ObsoleteCoroutinesApi
    override fun onMessage(payload: String) {
        Timber.d("WSS->C: $payload")
        if (connectionState == WebSocketConnectionState.CONNECTED || connectionState == WebSocketConnectionState.REGISTERED) {
            scope.launch {
                webSocketChannelEvents.onWebSocketMessage(payload)
            }
        }
    }
}