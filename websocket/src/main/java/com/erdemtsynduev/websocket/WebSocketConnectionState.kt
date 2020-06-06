package com.erdemtsynduev.websocket

/**
 * Possible WebSocket connection states.
 */
enum class WebSocketConnectionState {
    NEW, CONNECTED, REGISTERED, CLOSED, ERROR
}