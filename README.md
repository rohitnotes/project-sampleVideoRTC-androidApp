# Sample VideoRTC Android App

This Android sample app demonstrates how to establish WebRTC video call. It's based on the
original [WebRTC Android sample](https://webrtc.org/native-code/android/), slightly modified
to easily run on Android devices.

Two WebRTC clients need to exchange session description when establishing video call connection
via a signal server. This sample uses a hosted version of the [WebRTC signaling server](https://appr.tc).
Please refer to https://www.html5rocks.com/en/tutorials/webrtc/infrastructure/ for more information
on WebRTC signaling.

## Pre-requisites

1. Android Studio 3+

## WebRTC-specific dependencies

This sample has the following dependencies but they are either already included or directly linked
so that no additional steps are necessary.

* [WebRTC signaling server](https://appr.tc): The sample is set up to use the one hosted in https://appr.tc,
[source code](https://github.com/webrtc/apprtc#apprtc-demo-code).
* [WebRTC library at bintray/JCenter] (https://bintray.com/google/webrtc/google-webrtc)

## Build and Run

Build this app in Android Studio and run it on an Android Things board:

* Deploy and run the `app` module, which creates and joins a room with random ID
* The room ID will be shown on the display and on logcat. Take note of it, so that you can join the room with another device

Join the room from another WebRTC client:

* From a web browser, go to https://appr.tc and enter the same room ID shown on the Android app display, or
* Use one of the native webRTC samples, like [Android](https://webrtc.org/native-code/android/) 
or [iOS](https://webrtc.org/native-code/ios/) app and enter the room ID to join

## Categories

- Android application

## Languages

- Kotlin

## License

See LICENSE
