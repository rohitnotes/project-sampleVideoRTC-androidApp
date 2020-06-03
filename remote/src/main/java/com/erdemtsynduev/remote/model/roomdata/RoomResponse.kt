package com.erdemtsynduev.remote.model.roomdata

import androidx.annotation.Keep
import com.erdemtsynduev.remote.model.roomdata.Params

@Keep
data class RoomResponse(
    val params: Params,
    val result: String
)