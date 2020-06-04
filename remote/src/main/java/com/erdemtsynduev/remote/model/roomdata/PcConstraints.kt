package com.erdemtsynduev.remote.model.roomdata

import com.google.gson.annotations.SerializedName

data class PcConstraints(
    @SerializedName("optional") val optional: List<Any>
)