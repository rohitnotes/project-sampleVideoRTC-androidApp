package com.erdemtsynduev.remote

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class AppRtcProviderRemoteShould {

    private val remoteService = mock<RemoteService>()
    val appRtcProviderRemote: AppRtcProviderRemote = AppRtcProviderRemoteImpl(remoteService)

    @Test
    fun checkDataRoomFromServer() {
        runBlocking {
            whenever(remoteService.requestRoom("123456")).then {
                Assert.assertNotNull(it)
            }
        }
    }
}
