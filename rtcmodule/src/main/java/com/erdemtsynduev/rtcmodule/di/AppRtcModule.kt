package com.erdemtsynduev.rtcmodule.di

import com.erdemtsynduev.rtcmodule.AppRtcModuleImpl
import org.koin.dsl.module

val appRtcModule = module {
    single { AppRtcModuleImpl(get()) }
}