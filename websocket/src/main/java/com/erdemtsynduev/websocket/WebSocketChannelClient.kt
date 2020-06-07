package com.erdemtsynduev.websocket

interface WebSocketChannelClient {
    suspend fun connect(wsUrl: String, postUrl: String)
    suspend fun disconnect()
    suspend fun register(roomID: String, clientID: String)
    fun getConnectionState(): WebSocketConnectionState?
}