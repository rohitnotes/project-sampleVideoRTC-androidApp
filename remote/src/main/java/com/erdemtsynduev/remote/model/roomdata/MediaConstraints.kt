package com.erdemtsynduev.remote.model.roomdata

import com.google.gson.annotations.SerializedName

data class MediaConstraints(
    @SerializedName("audio") val audio: Boolean,
    @SerializedName("video") val video: Boolean
)