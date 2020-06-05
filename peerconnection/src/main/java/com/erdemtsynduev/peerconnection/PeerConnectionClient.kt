package com.erdemtsynduev.peerconnection

import com.erdemtsynduev.peerconnection.parameters.PeerConnectionParameters
import org.webrtc.*
import java.util.*

class PeerConnectionClient : SdpObserver, PeerConnection.Observer {

    private var rootEglBase: EglBase? = null
    private var factory: PeerConnectionFactory? = null
    private var peerConnection: PeerConnection? = null
    private var options: PeerConnectionFactory.Options? = null
    private var audioSource: AudioSource? = null
    private var videoSource: VideoSource? = null
    private var videoCallEnabled = false
    private var preferIsac = false
    private var preferredVideoCodec: String? = null
    private var videoCapturerStopped = false
    private var isError = false
    private var statsTimer: Timer? = null
    private var localRender: VideoSink? = null
    private var remoteRenders: List<VideoRenderer.Callbacks>? = null
    private var signalingParameters: SignalingParameters? = null
    private var videoWidth = 0
    private var videoHeight = 0
    private var videoFps = 0
    private var audioConstraints: MediaConstraints? = null
    private var sdpMediaConstraints: MediaConstraints? = null
    private var peerConnectionParameters: PeerConnectionParameters? = null

    // Queued remote ICE candidates are consumed only after both local and
    // remote descriptions are set. Similarly local ICE candidates are sent to
    // remote peer after both local and remote description are set.
    private var queuedRemoteCandidates: List<IceCandidate>? = null
    private var events: PeerConnectionEvents? = null
    private var isInitiator = false
    private var localSdp // either offer or answer SDP
            : SessionDescription? = null
    private var mediaStream: MediaStream? = null
    private var videoCapturer: VideoCapturer? = null

    // enableVideo is set to true if video should be rendered and sent.
    private var renderVideo = false
    private var localVideoTrack: VideoTrack? = null
    private var remoteVideoTrack: VideoTrack? = null
    private var localVideoSender: RtpSender? = null

    // enableAudio is set to true if audio should be sent.
    private var enableAudio = false
    private var localAudioTrack: AudioTrack? = null
    private var dataChannel: DataChannel? = null
    private var dataChannelEnabled = false

    fun createPeerConnectionFactory(
        peerConnectionParameters: PeerConnectionParameters,
        events: PeerConnectionEvents
    ) {
        this.peerConnectionParameters = peerConnectionParameters
        this.events = events
        this.videoCallEnabled = peerConnectionParameters.videoCallEnabled
        this.dataChannelEnabled = peerConnectionParameters.dataChannelParameters != null
        // Reset variables to initial states.
        this.factory = null
        this.peerConnection = null
        this.preferIsac = false
        this.videoCapturerStopped = false
        this.isError = false
        this.queuedRemoteCandidates = null
        this.localSdp = null // either offer or answer SDP
        this.mediaStream = null
        this.videoCapturer = null
        this.renderVideo = true
        this.localVideoTrack = null
        this.remoteVideoTrack = null
        this.localVideoSender = null
        this.enableAudio = true
        this.localAudioTrack = null
        this.statsTimer = Timer()
    }

    fun setPeerConnectionFactoryOptions(options: PeerConnectionFactory.Options?) {
        this.options = options
    }

    override fun onSetFailure(p0: String?) {
        TODO("Not yet implemented")
    }

    override fun onSetSuccess() {
        TODO("Not yet implemented")
    }

    override fun onCreateSuccess(p0: SessionDescription?) {
        TODO("Not yet implemented")
    }

    override fun onCreateFailure(p0: String?) {
        TODO("Not yet implemented")
    }

    override fun onIceCandidate(p0: IceCandidate?) {
        TODO("Not yet implemented")
    }

    override fun onDataChannel(p0: DataChannel?) {
        TODO("Not yet implemented")
    }

    override fun onIceConnectionReceivingChange(p0: Boolean) {
        TODO("Not yet implemented")
    }

    override fun onIceConnectionChange(p0: PeerConnection.IceConnectionState?) {
        TODO("Not yet implemented")
    }

    override fun onIceGatheringChange(p0: PeerConnection.IceGatheringState?) {
        TODO("Not yet implemented")
    }

    override fun onAddStream(p0: MediaStream?) {
        TODO("Not yet implemented")
    }

    override fun onSignalingChange(p0: PeerConnection.SignalingState?) {
        TODO("Not yet implemented")
    }

    override fun onIceCandidatesRemoved(p0: Array<out IceCandidate>?) {
        TODO("Not yet implemented")
    }

    override fun onRemoveStream(p0: MediaStream?) {
        TODO("Not yet implemented")
    }

    override fun onRenegotiationNeeded() {
        TODO("Not yet implemented")
    }

    override fun onAddTrack(p0: RtpReceiver?, p1: Array<out MediaStream>?) {
        TODO("Not yet implemented")
    }

    companion object {
        const val VIDEO_TRACK_ID = "ARDAMSv0"
        const val AUDIO_TRACK_ID = "ARDAMSa0"
        const val VIDEO_TRACK_TYPE = "video"
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