package com.erdemtsynduev.peerconnection.parameters

/**
 * Peer connection parameters.
 */
data class DataChannelParameters(
    var ordered: Boolean = false,
    var maxRetransmitTimeMs: Int = 0,
    var maxRetransmits: Int = 0,
    var protocol: String? = null,
    var negotiated: Boolean = false,
    var id: Int = 0
)