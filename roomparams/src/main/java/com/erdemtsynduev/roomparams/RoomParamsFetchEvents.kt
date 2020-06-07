package com.erdemtsynduev.roomparams

import com.erdemtsynduev.roomparams.data.SignalingParameters

/**
 * Room parameters fetcher callbacks.
 */
interface RoomParamsFetchEvents {
    /**
     * Callback fired once the room's signaling parameters
     * SignalingParameters are extracted.
     */
    fun onSignalingParametersReady(params: SignalingParameters?)

    /**
     * Callback for room parameters extraction error.
     */
    fun onSignalingParametersError(description: String?)
}