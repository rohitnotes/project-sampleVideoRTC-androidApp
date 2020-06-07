package com.erdemtsynduev.rtcmodule

import com.erdemtsynduev.roomparams.data.RoomConnectionParameters
import org.webrtc.IceCandidate
import org.webrtc.SessionDescription

class WebSocketRTCClientImpl(var signalingEvents: SignalingEvents) : WebSocketRTCClient {

    private var roomState: ConnectionState? = ConnectionState.NEW

    override fun connectToRoom(roomConnectionParameters: RoomConnectionParameters?) {
        TODO("Not yet implemented")
    }

    override fun sendOfferSdp(sdp: SessionDescription?) {
        TODO("Not yet implemented")
    }

    override fun sendAnswerSdp(sdp: SessionDescription?) {
        TODO("Not yet implemented")
    }

    override fun sendLocalIceCandidate(candidate: IceCandidate?) {
        TODO("Not yet implemented")
    }

    override fun sendLocalIceCandidateRemovals(candidates: Array<IceCandidate?>?) {
        TODO("Not yet implemented")
    }

    override fun disconnectFromRoom() {
        TODO("Not yet implemented")
    }

    companion object {
        const val ROOM_JOIN = "join"
        const val ROOM_MESSAGE = "message"
        const val ROOM_LEAVE = "leave"
    }
}