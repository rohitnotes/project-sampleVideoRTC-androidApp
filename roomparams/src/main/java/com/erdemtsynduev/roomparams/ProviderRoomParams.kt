package com.erdemtsynduev.roomparams

interface ProviderRoomParams {
    suspend fun connectRoom(roomId: String): RoomResponse
    suspend fun requestTurnServers(url: String): TurnIceServerResponse
}