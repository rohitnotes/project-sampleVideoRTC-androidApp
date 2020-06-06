package com.erdemtsynduev.roomparams.data

/**
 * Struct holding the connection parameters of an AppRTC room.
 */
data class RoomConnectionParameters(
    var roomUrl: String? = null,
    var roomId: String? = null,
    var loopback: Boolean = false,
    var urlParameters: String? = null
)
