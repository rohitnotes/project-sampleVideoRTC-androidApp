object Versions {
    const val kotlin = "1.3.72"
    const val kotlin_coroutines = "1.3.5"

    const val android_x_core_ktx = "1.3.0"

    const val koin = "2.1.5"

    const val retrofit = "2.8.1"
    const val ok_http_interceptor = "4.5.0"

    const val junit = "4.13"

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

    const val core_ktx = "androidx.core:core-ktx:${Versions.android_x_core_ktx}"

    const val koin = "org.koin:koin-android:${Versions.koin}"
    const val koin_viewmodel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
    const val koin_scope = "org.koin:koin-android-scope:${Versions.koin}"
    const val koin_test = "org.koin:koin-test:${Versions.koin}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val ok_http_interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.ok_http_interceptor}"
    const val gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"

    const val junit = "junit:junit:${Versions.junit}"

    const val android_gradle_plugin = "com.android.tools.build:gradle:${Versions.gradle_android}"
    const val kotlin_gradle_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
}

object Modules {
    const val rtcmodule = ":rtcmodule"
    const val remote = ":remote"
}
