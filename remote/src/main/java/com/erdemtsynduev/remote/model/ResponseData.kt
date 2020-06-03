package com.erdemtsynduev.remote.model

import androidx.annotation.Keep

@Keep
data class ResponseData(
    val params: Params,
    val result: String
)