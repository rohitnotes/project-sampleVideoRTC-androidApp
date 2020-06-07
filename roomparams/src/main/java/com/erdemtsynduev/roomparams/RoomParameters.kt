package com.erdemtsynduev.roomparams

import com.erdemtsynduev.roomparams.result.AppRtcRoomResult
import com.erdemtsynduev.roomparams.result.TurnIceServerResult

interface RoomParameters {
    suspend fun connectRoomAppRtc(roomId: String): AppRtcRoomResult
    suspend fun getDataTurnIceServer(url: String): TurnIceServerResult
}