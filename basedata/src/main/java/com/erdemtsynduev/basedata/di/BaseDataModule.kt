package com.erdemtsynduev.basedata.di

import com.erdemtsynduev.basedata.StringResolver
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val baseDataModule = module {
    single { StringResolver(androidContext()) }
}