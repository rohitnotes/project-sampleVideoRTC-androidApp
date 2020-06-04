package com.erdemtsynduev.remote.model.roomdata

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Params(
    @SerializedName("bypass_join_confirmation") val bypass_join_confirmation: Boolean,
    @SerializedName("client_id") val client_id: String,
    @SerializedName("error_messages") val error_messages: List<Any>,
    @SerializedName("ice_server_transports") val ice_server_transports: String,
    @SerializedName("ice_server_url") val ice_server_url: String,
    @SerializedName("include_loopback_js") val include_loopback_js: String,
    @SerializedName("is_initiator") val is_initiator: Boolean,
    @SerializedName("is_loopback") val is_loopback: Boolean,
    @SerializedName("media_constraints") val media_constraints: MediaConstraints,
    @SerializedName("messages") val messages: List<Messages>,
    @SerializedName("offer_options") val offer_options: OfferOptions,
    @SerializedName("pc_config") val pc_config: PcConfig,
    @SerializedName("pc_constraints") val pc_constraints: PcConstraints,
    @SerializedName("room_id") val room_id: String,
    @SerializedName("room_link") val room_link: String,
    @SerializedName("version_info") val version_info: VersionInfo,
    @SerializedName("warning_messages") val warning_messages: List<Any>,
    @SerializedName("wss_post_url") val wss_post_url: String,
    @SerializedName("wss_url") val wss_url: String
)