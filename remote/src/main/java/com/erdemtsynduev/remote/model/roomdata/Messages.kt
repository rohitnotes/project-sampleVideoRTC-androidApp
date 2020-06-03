package com.erdemtsynduev.remote.model.roomdata

import androidx.annotation.Keep

@Keep
data class Messages(
    val candidate: String,
    val id: String,
    val label: Int,
    val type: String,
    val sdp: String
)