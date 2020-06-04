package com.erdemtsynduev.remote.model.roomdata

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class VersionInfo(
    @SerializedName("branch") val branch: String,
    @SerializedName("gitHash") val gitHash: String,
    @SerializedName("time") val time: String
)