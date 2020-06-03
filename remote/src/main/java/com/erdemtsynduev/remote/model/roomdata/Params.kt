package com.erdemtsynduev.remote.model.roomdata

import androidx.annotation.Keep
import com.erdemtsynduev.remote.model.roomdata.Messages

@Keep
data class Params(
    val bypass_join_confirmation: String,
    val client_id: String,
    val error_messages: List<Any>,
    val ice_server_transports: String,
    val ice_server_url: String,
    val include_loopback_js: String,
    val is_initiator: Boolean,
    val is_loopback: Boolean,
    val media_constraints: MediaConstraints,
    val messages: List<Messages>,
    val offer_options: String,
    val pc_config: PcConfig,
    val pc_constraints: PcConstraints,
    val room_id: String,
    val room_link: String,
    val version_info: VersionInfo,
    val warning_messages: List<Any>,
    val wss_post_url: String,
    val wss_url: String
)