package com.erdemtsynduev.rtcmodule

import androidx.annotation.WorkerThread
import com.erdemtsynduev.remote.model.roomdata.IceServers
import com.erdemtsynduev.remote.model.roomdata.RoomResponse
import com.erdemtsynduev.rtcmodule.AppRTCClient.SignalingParameters
import com.erdemtsynduev.rtcmodule.result.AppRtcRoomResult
import com.erdemtsynduev.rtcmodule.result.TurnIceServerResult
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.webrtc.IceCandidate
import org.webrtc.PeerConnection
import org.webrtc.SessionDescription

class RoomParamsFetch(val events: RoomParamsFetchEvents) : KoinComponent {

    private val appRtcModuleImpl: AppRtcModuleImpl by inject()

    @WorkerThread
    suspend fun makeRequest(roomUrl: String) {
        val roomData = appRtcModuleImpl.connectRoomAppRtc(roomUrl)
        when (roomData) {
            is AppRtcRoomResult.Success -> {
                parseData(roomData.roomResponse)
            }
            is AppRtcRoomResult.Error -> {
            }
        }
    }

    @WorkerThread
    suspend fun requestTurnServers(ice_server_url: String): List<PeerConnection.IceServer> {
        val turnServers: ArrayList<PeerConnection.IceServer> = ArrayList()
        val turnIceServerData = appRtcModuleImpl.getDataTurnIceServer(ice_server_url)
        return when (turnIceServerData) {
            is TurnIceServerResult.Success -> {
                turnIceServerData.responseData.iceServers.forEach {
                    val turnServer =
                        PeerConnection.IceServer.builder(it.urls)
                            .setUsername(it.username)
                            .setPassword(it.credential)
                            .createIceServer()
                    turnServers.add(turnServer)
                }
                return turnServers
            }
            is TurnIceServerResult.Error -> {
                return turnServers
            }
        }
    }

    private suspend fun parseData(roomDataRoomResponse: RoomResponse) {
        val iceCandidates: ArrayList<IceCandidate> = ArrayList()
        var offerSdp: SessionDescription? = null
        val iceServers: ArrayList<PeerConnection.IceServer> =
            iceServersFromPCConfig(roomDataRoomResponse.params.pc_config.iceServers)

        var isTurnPresent = false
        for (server in iceServers) {
            for (uri in server.urls) {
                if (uri.startsWith("turn:")) {
                    isTurnPresent = true
                    break
                }
            }
        }

        // Request TURN servers.
        if (!(isTurnPresent || roomDataRoomResponse.params.ice_server_url.isEmpty())) {
            val turnServers: List<PeerConnection.IceServer> =
                requestTurnServers(roomDataRoomResponse.params.ice_server_url)
            for (turnServer in turnServers) {
                iceServers.add(turnServer)
            }
        }

        roomDataRoomResponse.params.messages.forEach {
            if (it.type == "offer") {
                offerSdp =
                    SessionDescription(SessionDescription.Type.fromCanonicalForm(it.type), it.sdp)
            } else if (it.type == "candidate") {
                val candidate = IceCandidate(
                    it.id, it.label, it.candidate
                )
                iceCandidates.add(candidate)
            }
        }

        val params = SignalingParameters(
            iceServers,
            roomDataRoomResponse.params.is_initiator,
            roomDataRoomResponse.params.client_id,
            roomDataRoomResponse.params.wss_url,
            roomDataRoomResponse.params.wss_post_url,
            offerSdp,
            iceCandidates
        )
        events.onSignalingParametersReady(params)
    }

    // Return the list of ICE servers described by a WebRTCPeerConnection
    // configuration string.
    private fun iceServersFromPCConfig(listIceServers: List<IceServers>): ArrayList<PeerConnection.IceServer> {
        val ret: ArrayList<PeerConnection.IceServer> = ArrayList()
        listIceServers.forEach {
            val turnServer = PeerConnection.IceServer.builder(it.urls)
                .setPassword(it.credential)
                .createIceServer()
            ret.add(turnServer)
        }
        return ret
    }
}