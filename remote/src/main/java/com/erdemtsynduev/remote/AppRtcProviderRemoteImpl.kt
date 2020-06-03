package com.erdemtsynduev.remote

import com.erdemtsynduev.remote.model.ResponseData

internal class AppRtcProviderRemoteImpl(private val remoteService: RemoteService) :
    AppRtcProviderRemote {

    override suspend fun connectRoom(roomId: String): ResponseData {
        return remoteService.requestRoom(roomId)
    }
}
