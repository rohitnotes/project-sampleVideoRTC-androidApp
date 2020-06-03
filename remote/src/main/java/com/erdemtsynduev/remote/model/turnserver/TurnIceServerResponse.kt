package com.erdemtsynduev.remote.model.turnserver

import androidx.annotation.Keep

@Keep
data class TurnIceServerResponse(
    val blockStatus: String,
    val iceServers: List<IceServer>,
    val iceTransportPolicy: String,
    val lifetimeDuration: String
)