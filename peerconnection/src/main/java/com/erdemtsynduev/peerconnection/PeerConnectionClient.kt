package com.erdemtsynduev.peerconnection

class PeerConnectionClient() {

    companion object {
        const val VIDEO_TRACK_ID = "ARDAMSv0"
        const val AUDIO_TRACK_ID = "ARDAMSa0"
        const val VIDEO_TRACK_TYPE = "video"
        const val TAG = "PCRTCClient"
        const val VIDEO_CODEC_VP8 = "VP8"
        const val VIDEO_CODEC_VP9 = "VP9"
        const val VIDEO_CODEC_H264 = "H264"
        const val VIDEO_CODEC_H264_BASELINE = "H264 Baseline"
        const val VIDEO_CODEC_H264_HIGH = "H264 High"
        const val AUDIO_CODEC_OPUS = "opus"
        const val AUDIO_CODEC_ISAC = "ISAC"
        const val VIDEO_CODEC_PARAM_START_BITRATE = "x-google-start-bitrate"
        const val VIDEO_FLEXFEC_FIELDTRIAL =
            "WebRTC-FlexFEC-03-Advertised/Enabled/WebRTC-FlexFEC-03/Enabled/"
        const val VIDEO_VP8_INTEL_HW_ENCODER_FIELDTRIAL =
            "WebRTC-IntelVP8/Enabled/"
        const val VIDEO_H264_HIGH_PROFILE_FIELDTRIAL =
            "WebRTC-H264HighProfile/Enabled/"
        const val DISABLE_WEBRTC_AGC_FIELDTRIAL =
            "WebRTC-Audio-MinimizeResamplingOnMobile/Enabled/"
        val VIDEO_FRAME_EMIT_FIELDTRIAL: String =
            (org.webrtc.PeerConnectionFactory.VIDEO_FRAME_EMIT_TRIAL + "/" + org.webrtc.PeerConnectionFactory.TRIAL_ENABLED + "/")
        const val AUDIO_CODEC_PARAM_BITRATE = "maxaveragebitrate"
        const val AUDIO_ECHO_CANCELLATION_CONSTRAINT = "googEchoCancellation"
        const val AUDIO_AUTO_GAIN_CONTROL_CONSTRAINT = "googAutoGainControl"
        const val AUDIO_HIGH_PASS_FILTER_CONSTRAINT = "googHighpassFilter"
        const val AUDIO_NOISE_SUPPRESSION_CONSTRAINT = "googNoiseSuppression"
        const val AUDIO_LEVEL_CONTROL_CONSTRAINT = "levelControl"
        const val DTLS_SRTP_KEY_AGREEMENT_CONSTRAINT = "DtlsSrtpKeyAgreement"
        const val HD_VIDEO_WIDTH = 1280
        const val HD_VIDEO_HEIGHT = 720
        const val BPS_IN_KBPS = 1000
    }
}