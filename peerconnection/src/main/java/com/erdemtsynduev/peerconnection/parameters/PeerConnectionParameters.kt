package com.erdemtsynduev.peerconnection.parameters

/**
 * Peer connection parameters.
 */
data class PeerConnectionParameters(
    var videoCallEnabled: Boolean = false,
    var loopback: Boolean = false,
    var tracing: Boolean = false,
    var videoWidth: Int = 0,
    var videoHeight: Int = 0,
    var videoFps: Int = 0,
    var videoMaxBitrate: Int = 0,
    var videoCodec: String? = null,
    var videoCodecHwAcceleration: Boolean = false,
    var videoFlexfecEnabled: Boolean = false,
    var audioStartBitrate: Int = 0,
    var audioCodec: String? = null,
    var noAudioProcessing: Boolean = false,
    var aecDump: Boolean = false,
    var useOpenSLES: Boolean = false,
    var disableBuiltInAEC: Boolean = false,
    var disableBuiltInAGC: Boolean = false,
    var disableBuiltInNS: Boolean = false,
    var enableLevelControl: Boolean = false,
    var disableWebRtcAGCAndHPF: Boolean = false,
    var dataChannelParameters: DataChannelParameters? = null
)