package com.erdemtsynduev.remote.model.roomdata

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Messages(
    @SerializedName("candidate") val candidate: String,
    @SerializedName("id") val id: String,
    @SerializedName("label") val label: Int,
    @SerializedName("type") val type: String,
    @SerializedName("sdp") val sdp: String
)