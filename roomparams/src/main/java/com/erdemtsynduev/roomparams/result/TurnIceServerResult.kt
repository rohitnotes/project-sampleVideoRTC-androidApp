package com.erdemtsynduev.roomparams.result

import com.erdemtsynduev.remote.model.turnserver.TurnIceServerResponse

sealed class TurnIceServerResult {
    data class Success(val responseData: TurnIceServerResponse) : TurnIceServerResult()
    object Error : TurnIceServerResult()
}