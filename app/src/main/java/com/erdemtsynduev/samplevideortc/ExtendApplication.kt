package com.erdemtsynduev.samplevideortc

import android.app.Application
import com.erdemtsynduev.basedata.di.baseDataModule
import com.erdemtsynduev.remote.di.remoteModule
import com.erdemtsynduev.rtcmodule.di.appRtcModule
import com.erdemtsynduev.samplevideortc.di.Logger
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ExtendApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            logger(Logger())
            androidContext(this@ExtendApplication)
            modules(
                mutableListOf(
                    baseDataModule,
                    remoteModule,
                    appRtcModule
                ).apply {
                }
            )
        }
    }
}
