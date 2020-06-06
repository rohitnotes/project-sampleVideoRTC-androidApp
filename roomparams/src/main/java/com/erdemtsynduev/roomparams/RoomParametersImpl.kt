package com.erdemtsynduev.roomparams

import com.erdemtsynduev.remote.AppRtcProviderRemote

class RoomParametersImpl(private val appRtcProviderRemote: AppRtcProviderRemote) {

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

    suspend fun fetchRemoteData(roomId: String): AppRtcRoomResult {
        val remoteData = appRtcProviderRemote.connectRoom(roomId)
        return if (remoteData.result == "SUCCESS") {
            AppRtcRoomResult.Success(remoteData)
        } else {
            AppRtcRoomResult.Error
        }
    }

    suspend fun fetchTurnIceServerData(url: String): TurnIceServerResult {
        val remoteData = appRtcProviderRemote.requestTurnServers(url)
        return TurnIceServerResult.Success(remoteData)
    }
}
