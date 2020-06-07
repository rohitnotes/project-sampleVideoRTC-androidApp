package com.erdemtsynduev.websocket

interface WebSocketChannelClient {
    suspend fun connect(webSocketUrl: String, postUrl: String)
    suspend fun disconnect()
    suspend fun register(roomID: String, clientID: String)
    fun getConnectionState(): WebSocketConnectionState?
}