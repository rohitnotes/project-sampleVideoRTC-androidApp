package com.erdemtsynduev.remote

import com.erdemtsynduev.remote.model.roomdata.RoomResponse
import com.erdemtsynduev.remote.model.turnserver.TurnIceServerResponse
import retrofit2.Retrofit
import retrofit2.http.*

internal interface RemoteService {

    /**
     * Request to connect to the room
     */
    @Headers(
        "Content-Type: text/plain; charset=utf-8",
        "origin: https://appr.tc"
    )
    @POST("join/{room_id}")
    suspend fun requestRoom(@Path("room_id") roomId: String): RoomResponse

    /**
     * Request to Turn server
     */
    @Headers(
        "Content-Type: text/plain; charset=utf-8",
        "origin: https://appr.tc"
    )
    @POST
    suspend fun requestTurnServers(@Url urlRequest: String): TurnIceServerResponse

    /**
     * Asynchronously send POST to WebSocket server.
     */
    @Headers(
        "Content-Type: text/plain; charset=utf-8",
        "origin: https://appr.tc"
    )
    @POST
    suspend fun requestPostToWebSocketServer(@Url urlRequest: String, @Body body: String): String

    /**
     * Asynchronously send DELETE to WebSocket server.
     */
    @Headers(
        "Content-Type: text/plain; charset=utf-8",
        "origin: https://appr.tc"
    )
    @DELETE
    suspend fun requestDeleteToWebSocketServer(@Url urlRequest: String, @Body body: String): String
}

internal fun getRemoteService(retrofit: Retrofit) = retrofit.create(RemoteService::class.java)
