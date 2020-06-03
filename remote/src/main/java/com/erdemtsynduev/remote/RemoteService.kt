package com.erdemtsynduev.remote

import com.erdemtsynduev.remote.model.roomdata.RoomResponse
import com.erdemtsynduev.remote.model.turnserver.TurnIceServerResponse
import retrofit2.Retrofit
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Url

internal interface RemoteService {

    /**
     * Request to connect to the room
     */
    @POST("join/{room_id}")
    suspend fun requestRoom(@Path("room_id") roomId: String): RoomResponse

    /**
     * Request to Turn server
     */
    @POST
    suspend fun requestTurnServers(@Url urlRequest: String): TurnIceServerResponse
}

internal fun getRemoteService(retrofit: Retrofit) = retrofit.create(RemoteService::class.java)
