package com.erdemtsynduev.remote.model.roomdata

import androidx.annotation.Keep

@Keep
data class PcConfig(
    val bundlePolicy: String,
    val iceServers: List<IceServers>,
    val rtcpMuxPolicy: String
)