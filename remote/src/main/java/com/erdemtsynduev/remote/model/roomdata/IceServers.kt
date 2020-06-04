package com.erdemtsynduev.remote.model.roomdata

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class IceServers(
    @SerializedName("urls") val urls: String,
    @SerializedName("credential") val credential: String
)