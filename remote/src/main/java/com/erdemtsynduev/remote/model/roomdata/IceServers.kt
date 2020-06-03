package com.erdemtsynduev.remote.model.roomdata

import androidx.annotation.Keep

@Keep
data class IceServers(
    val urls: String,
    val credential: String
)