package com.lexwilliam.data_remote.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.lexwilliam.MainCoroutineRule
import com.lexwilliam.data.OAuthRemoteSource
import com.lexwilliam.data.UserRemoteSource
import com.lexwilliam.data_remote.JikanService
import com.lexwilliam.data_remote.MyAnimeListService
import com.lexwilliam.data_remote.mapper.AnimeMapper
import com.lexwilliam.data_remote.mapper.OAuthMapper
import com.lexwilliam.data_remote.mapper.UserMapper
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
class UserRemoteSourceImplTest {
    private lateinit var userRemoteSource: UserRemoteSource

    @Mock
    lateinit var malService: MyAnimeListService

    @Mock
    lateinit var jikanService: JikanService

    @Mock
    lateinit var userMapper: UserMapper

    @Mock
    lateinit var animeMapper: AnimeMapper

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @JvmField
    @Rule
    val mainCoroutineRule = MainCoroutineRule()

    @FlowPreview
    @Before
    fun setUp() {
        userRemoteSource = UserRemoteSourceImpl(
            malService,
            jikanService,
            animeMapper,
            userMapper
        )
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `When getUserProfile then malService invoked`() {
        runTest {
            // When
            whenever(jikanService.getUserProfile("Error404G")).thenReturn(mock())
            whenever(malService.getUserInfo("")).thenReturn(mock())

            userRemoteSource.getUserProfile("")

            // Then
            verify(jikanService, times(1)).getUserProfile("Error404G")
            verify(malService, times(1)).getUserInfo("")
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `When getUserAnimeList then malService invoked`() {
        runTest {
            // When
            whenever(malService.getAccessToken("", "", "")).thenReturn(mock())

            userRemoteSource.getUserAnimeList("")

            // Then
            verify(malService, times(1)).getAccessToken("", "", "")
        }
    }
}