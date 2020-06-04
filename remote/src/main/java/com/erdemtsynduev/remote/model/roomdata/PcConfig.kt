package com.erdemtsynduev.remote.model.roomdata

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class PcConfig(
    @SerializedName("bundlePolicy") val bundlePolicy: String,
    @SerializedName("iceServers") val iceServers: List<IceServers>,
    @SerializedName("rtcpMuxPolicy") val rtcpMuxPolicy: String
)