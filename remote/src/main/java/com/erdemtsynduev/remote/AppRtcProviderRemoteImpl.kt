package com.erdemtsynduev.remote

import com.erdemtsynduev.remote.model.roomdata.RoomResponse
import com.erdemtsynduev.remote.model.turnserver.TurnIceServerResponse

internal class AppRtcProviderRemoteImpl(private val remoteService: RemoteService) :
    AppRtcProviderRemote {

    override suspend fun connectRoom(roomId: String): RoomResponse {
        return remoteService.requestRoom(roomId)
    }

    override suspend fun requestTurnServers(url: String): TurnIceServerResponse {
        return remoteService.requestTurnServers(url)
    }
}
