package com.erdemtsynduev.roomparams.di

import org.koin.dsl.module

val appRtcModule = module {
    single { AppRtcModuleImpl(get()) }
}