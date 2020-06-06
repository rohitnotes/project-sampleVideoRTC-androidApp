package com.erdemtsynduev.roomparams.data

import org.webrtc.IceCandidate
import org.webrtc.PeerConnection
import org.webrtc.SessionDescription

/**
 * Struct holding the signaling parameters of an AppRTC room.
 */
data class SignalingParameters(
    var iceServers: List<PeerConnection.IceServer>? = null,
    var initiator: Boolean = false,
    var clientId: String? = null,
    var wssUrl: String? = null,
    var wssPostUrl: String? = null,
    var offerSdp: SessionDescription? = null,
    var iceCandidates: List<IceCandidate>? = null
)