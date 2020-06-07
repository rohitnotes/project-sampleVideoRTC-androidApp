package com.erdemtsynduev.roomparams

import com.erdemtsynduev.remote.AppRtcProviderRemote
import com.erdemtsynduev.roomparams.result.AppRtcRoomResult
import com.erdemtsynduev.roomparams.result.TurnIceServerResult

class RoomParametersNetworkImpl(private val appRtcProviderRemote: AppRtcProviderRemote) : RoomParametersNetwork {

    override suspend fun connectRoomAppRtc(roomId: String): AppRtcRoomResult {
        return (try {
            fetchRemoteData(roomId)
        } catch (e: Exception) {
            AppRtcRoomResult.Error
        })
    }

    override suspend fun getDataTurnIceServer(url: String): TurnIceServerResult {
        return (try {
            fetchTurnIceServerData(url)
        } catch (e: Exception) {
            TurnIceServerResult.Error
        })
    }

    private suspend fun fetchRemoteData(roomId: String): AppRtcRoomResult {
        val remoteData = appRtcProviderRemote.connectRoom(roomId)
        return if (remoteData.result == "SUCCESS") {
            AppRtcRoomResult.Success(remoteData)
        } else {
            AppRtcRoomResult.Error
        }
    }

    private suspend fun fetchTurnIceServerData(url: String): TurnIceServerResult {
        val remoteData = appRtcProviderRemote.requestTurnServers(url)
        return TurnIceServerResult.Success(remoteData)
    }
}
