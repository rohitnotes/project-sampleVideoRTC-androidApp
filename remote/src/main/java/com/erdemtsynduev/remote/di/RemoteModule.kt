package com.erdemtsynduev.remote.di

import com.erdemtsynduev.remote.*
import com.erdemtsynduev.remote.AppRtcProviderRemoteImpl
import com.erdemtsynduev.remote.getRemoteService
import com.erdemtsynduev.remote.provideRetrofit
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module

val remoteModule = module {
    single { HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY } }
    single { provideOkHttpClient(get()) }
    single { provideRetrofit(get(), get()) }
    single { provideGson() }
    single { getRemoteService(get()) }
    single<AppRtcProviderRemote> { AppRtcProviderRemoteImpl(get()) }
}
