package com.erdemtsynduev.roomparams.di

import com.erdemtsynduev.roomparams.RoomParametersNetworkImpl
import org.koin.dsl.module

val roomParamsModule = module {
    single { RoomParametersNetworkImpl(get()) }
}