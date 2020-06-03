package com.erdemtsynduev.remote

import com.erdemtsynduev.remote.model.ResponseData
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

internal interface RemoteService {

    /**
     * Request to connect to the room
     */
    @POST("join/{room_id}")
    suspend fun requestRoom(@Path("room_id") roomId: String): ResponseData
}

internal fun getRemoteService(retrofit: Retrofit) = retrofit.create(RemoteService::class.java)
