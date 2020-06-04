package com.erdemtsynduev.remote.model.roomdata

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class RoomResponse(
    @SerializedName("params") val params: Params,
    @SerializedName("result") val result: String
)