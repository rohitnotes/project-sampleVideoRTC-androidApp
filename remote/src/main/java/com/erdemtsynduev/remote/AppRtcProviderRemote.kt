package com.erdemtsynduev.remote

import com.erdemtsynduev.remote.model.ResponseData

interface AppRtcProviderRemote {
    suspend fun connectRoom(roomId: String): ResponseData
}
