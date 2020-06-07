package com.erdemtsynduev.rtcmodule

import com.erdemtsynduev.roomparams.data.RoomConnectionParameters
import org.webrtc.IceCandidate
import org.webrtc.SessionDescription

/**
 * AppRTCClient is the interface representing an AppRTC client.
 */
interface WebSocketRTCClient {

    /**
     * Asynchronously connect to an AppRTC room URL using supplied connection
     * parameters. Once connection is established onConnectedToRoom()
     * callback with room parameters is invoked.
     */
    fun connectToRoom(roomConnectionParameters: RoomConnectionParameters?)

    /**
     * Send offer SDP to the other participant.
     */
    fun sendOfferSdp(sdp: SessionDescription?)

    /**
     * Send answer SDP to the other participant.
     */
    fun sendAnswerSdp(sdp: SessionDescription?)

    /**
     * Send Ice candidate to the other participant.
     */
    fun sendLocalIceCandidate(candidate: IceCandidate?)

    /**
     * Send removed ICE candidates to the other participant.
     */
    fun sendLocalIceCandidateRemovals(candidates: Array<IceCandidate?>?)

    /**
     * Disconnect from room.
     */
    fun disconnectFromRoom()
}