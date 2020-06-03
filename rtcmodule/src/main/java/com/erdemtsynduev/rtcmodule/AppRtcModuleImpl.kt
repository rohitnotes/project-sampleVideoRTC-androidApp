package com.erdemtsynduev.rtcmodule

import com.erdemtsynduev.remote.AppRtcProviderRemote
import java.lang.Exception

internal class AppRtcModuleImpl(private val appRtcProviderRemote: AppRtcProviderRemote) {

    suspend fun connectRoomAppRtc(roomId: String): AppRtcResult {
        return (try {
            fetchRemoteData(roomId)
        } catch (e: Exception) {
            AppRtcResult.Error
        })
    }

    private suspend fun fetchRemoteData(roomId: String): AppRtcResult {
        val remoteData = appRtcProviderRemote.connectRoom(roomId)
        return AppRtcResult.Success(remoteData)
    }
}
