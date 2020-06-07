package com.erdemtsynduev.roomparams.di

import com.erdemtsynduev.roomparams.RoomParametersImpl
import org.koin.dsl.module

val roomParamsModule = module {
    single { RoomParametersImpl(get()) }
}