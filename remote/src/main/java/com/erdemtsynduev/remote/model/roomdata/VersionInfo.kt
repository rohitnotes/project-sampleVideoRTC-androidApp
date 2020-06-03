package com.erdemtsynduev.remote.model.roomdata

import androidx.annotation.Keep

@Keep
data class VersionInfo(
    val branch: String,
    val gitHash: String,
    val time: String
)