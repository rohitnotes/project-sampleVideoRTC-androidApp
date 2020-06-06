package com.erdemtsynduev.roomparams

interface RoomParameters {
    suspend fun connectRoom(roomId: String): RoomResponse
    suspend fun requestTurnServers(url: String): TurnIceServerResponse
}