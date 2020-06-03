package com.erdemtsynduev.remote

import com.erdemtsynduev.remote.model.roomdata.RoomResponse
import com.erdemtsynduev.remote.model.turnserver.TurnIceServerResponse

interface AppRtcProviderRemote {
    suspend fun connectRoom(roomId: String): RoomResponse
    suspend fun requestTurnServers(url: String): TurnIceServerResponse
}
