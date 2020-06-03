package com.erdemtsynduev.rtcmodule

import com.erdemtsynduev.remote.model.ResponseData

sealed class AppRtcResult {
    data class Success(val responseData: ResponseData) : AppRtcResult()
    object Error : AppRtcResult()
}