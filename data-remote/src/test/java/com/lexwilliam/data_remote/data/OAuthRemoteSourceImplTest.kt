package com.lexwilliam.data_remote.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.lexwilliam.MainCoroutineRule
import com.lexwilliam.data.OAuthRemoteSource
import com.lexwilliam.data_remote.MyAnimeListService
import com.lexwilliam.data_remote.mapper.OAuthMapper
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class OAuthRemoteSourceImplTest {
    private lateinit var oAuthRemoteSource: OAuthRemoteSource

    @Mock
    lateinit var malService: MyAnimeListService

    @Mock
    lateinit var oAuthMapper: OAuthMapper

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @JvmField
    @Rule
    val mainCoroutineRule = MainCoroutineRule()

    @FlowPreview
    @Before
    fun setUp() {
        oAuthRemoteSource = OAuthRemoteSourceImpl(
            malService,
            oAuthMapper
        )
    }

//    @ExperimentalCoroutinesApi
//    @Test
//    fun `When refreshToken then malService invoked`() {
//        runTest {
//            // When
//            whenever(malService.refreshToken("", "", "")).thenReturn(mock())
//
//            oAuthRemoteSource.refreshToken("", "")
//
//            // Then
//            verify(malService, times(1)).refreshToken("", "", "")
//        }
//    }
//
//    @ExperimentalCoroutinesApi
//    @Test
//    fun `When getAccessToken then malService invoked`() {
//        runTest {
//            // When
//            whenever(malService.getAccessToken("", "", "")).thenReturn(mock())
//
//            oAuthRemoteSource.getAccessToken("", "", "")
//
//            // Then
//            verify(malService, times(1)).getAccessToken("", "", "")
//        }
//    }
}
