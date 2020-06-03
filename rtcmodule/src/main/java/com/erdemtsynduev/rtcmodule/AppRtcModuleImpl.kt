package com.erdemtsynduev.rtcmodule

import com.erdemtsynduev.remote.AppRtcProviderRemote
import com.erdemtsynduev.rtcmodule.result.AppRtcRoomResult
import com.erdemtsynduev.rtcmodule.result.TurnIceServerResult
import java.lang.Exception

class AppRtcModuleImpl(private val appRtcProviderRemote: AppRtcProviderRemote) {

    suspend fun connectRoomAppRtc(roomId: String): AppRtcRoomResult {
        return (try {
            fetchRemoteData(roomId)
        } catch (e: Exception) {
            AppRtcRoomResult.Error
        })
    }

    suspend fun getDataTurnIceServer(url: String): TurnIceServerResult {
        return (try {
            fetchTurnIceServerData(url)
        } catch (e: Exception) {
            TurnIceServerResult.Error
        })
    }

    suspend private fun fetchRemoteData(roomId: String): AppRtcRoomResult {
        val remoteData = appRtcProviderRemote.connectRoom(roomId)
        return if (remoteData.result == "SUCCESS") {
            AppRtcRoomResult.Success(remoteData)
        } else {
            AppRtcRoomResult.Error
        }
    }

    suspend private fun fetchTurnIceServerData(url: String): TurnIceServerResult {
        val remoteData = appRtcProviderRemote.requestTurnServers(url)
        return TurnIceServerResult.Success(remoteData)
    }
}
