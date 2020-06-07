package com.erdemtsynduev.roomparams.result

import com.erdemtsynduev.remote.model.roomdata.RoomResponse

sealed class AppRtcRoomResult {
    data class Success(val roomResponse: RoomResponse) : AppRtcRoomResult()
    object Error : AppRtcRoomResult()
}