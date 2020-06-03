package com.erdemtsynduev.remote.model.turnserver

import androidx.annotation.Keep

@Keep
data class IceServer(
    val credential: String,
    val maxRateKbps: String,
    val urls: List<String>,
    val username: String
)