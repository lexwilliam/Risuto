package com.lexwilliam.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.lexwilliam.MainCoroutineRule
import com.lexwilliam.data.OAuthLocalSource
import com.lexwilliam.data.OAuthRemoteSource
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class OAuthRepositoryImplTest {
    private lateinit var oAuthRepository: OAuthRepositoryImpl

    @Mock
    lateinit var oAuthLocalSource: OAuthLocalSource

    @Mock
    lateinit var oAuthRemoteSource: OAuthRemoteSource

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @JvmField
    @Rule
    val mainCoroutineRule = MainCoroutineRule()

    @FlowPreview
    @Before
    fun setUp() {
        oAuthRepository = OAuthRepositoryImpl(
            oAuthRemoteSource, oAuthLocalSource
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `When getAuthTokenLink then oAuthRemoteSource invoked`() {
        runTest {
            // When
            whenever(oAuthRemoteSource.getAuthTokenLink("", "", "", "")).thenReturn(mock())

            oAuthRepository.getAuthTokenLink("", "", "")

            // Then
            verify(oAuthRemoteSource, times(1)).getAuthTokenLink("", "", "", "")
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `When setRefreshToken then oAuthRemoteSource invoked`() {
        runTest {
            // When
            whenever(oAuthRemoteSource.getAuthTokenLink("", "", "", "")).thenReturn(mock())

            oAuthRepository.getAuthTokenLink("", "", "")

            // Then
            verify(oAuthRemoteSource, times(1)).getAuthTokenLink("", "", "", "")
        }
    }
}