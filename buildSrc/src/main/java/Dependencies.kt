object Versions {
    const val kotlin = "1.3.72"
    const val kotlin_coroutines = "1.3.5"

    const val androidx_core_ktx = "1.3.0"
    const val androidx_annotation = "1.1.0"

    const val koin = "2.1.5"

    const val retrofit = "2.8.1"
    const val ok_http_interceptor = "4.5.0"
    const val gson = "2.8.6"

    const val webrtc = "1.0.23995"
    const val timber = "4.7.1"
    const val autobahn = "20.2.1"

    const val junit = "4.13"
    const val mockito = "3.3.3"
    const val mockito_kotlin = "2.2.0"
    const val truth = "1.0.1"
    const val robolectric = "4.3.1"
    const val androidx_junit = "1.1.1"

    const val gradle_android = "4.0.0"

    const val min_sdk = 16
    const val target_sdk = 29
    const val compile_sdk = 29
    const val build_tools = "29.0.3"

    const val version_code = 10000
    const val version_name = "1.0.0"
}

object Deps {
    const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    const val kotlin_coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlin_coroutines}"
    const val kotlin_coroutines_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlin_coroutines}"

    const val core_ktx = "androidx.core:core-ktx:${Versions.androidx_core_ktx}"
    const val androidx_annotation = "androidx.annotation:annotation:${Versions.androidx_annotation}"

    const val koin = "org.koin:koin-android:${Versions.koin}"
    const val koin_viewmodel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
    const val koin_scope = "org.koin:koin-android-scope:${Versions.koin}"
    const val koin_test = "org.koin:koin-test:${Versions.koin}"

    const val webrtc = "org.webrtc:google-webrtc:${Versions.webrtc}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val autobahn = "io.crossbar.autobahn:autobahn-android:${Versions.autobahn}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val ok_http_interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.ok_http_interceptor}"
    const val converter_gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"

    const val junit = "junit:junit:${Versions.junit}"
    const val mockito_core = "org.mockito:mockito-core:${Versions.mockito}"
    const val mockito_inline = "org.mockito:mockito-inline:${Versions.mockito}"
    const val mockito_kotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockito_kotlin}"
    const val truth = "com.google.truth:truth:${Versions.truth}"
    const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
    const val androidx_junit = "androidx.test.ext:junit:${Versions.androidx_junit}"

    const val android_gradle_plugin = "com.android.tools.build:gradle:${Versions.gradle_android}"
    const val kotlin_gradle_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
}

object Modules {
    const val rtcmodule = ":rtcmodule"
    const val remote = ":remote"
    const val basedata = ":basedata"
    const val roomparams = ":roomparams"
    const val peerconnection = ":peerconnection"
    const val websocket = ":websocket"
}
