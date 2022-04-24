package com.lexwilliam.data_remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.lexwilliam.MainCoroutineRule
import com.lexwilliam.data.AnimeRemoteSource
import com.lexwilliam.data_remote.data.AnimeRemoteSourceImpl
import com.lexwilliam.data_remote.mapper.AnimeMapper
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
class AnimeRemoteSourceImplTest {
    private lateinit var animeRemoteSource: AnimeRemoteSource

    @Mock
    lateinit var jikanService: JikanService

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
        animeRemoteSource = AnimeRemoteSourceImpl(
            jikanService,
            animeMapper
        )
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `When getTopAnime then jikanService invoked`() {
        runTest {
            // When
            whenever(jikanService.getTopAnime()).thenReturn(mock())

            animeRemoteSource.getTopAnime()

            // Then
            verify(jikanService, times(1)).getTopAnime()
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `When getSeasonNow then jikanService invoked`() {
        runTest {
            // When
            whenever(jikanService.getSeasonNow()).thenReturn(mock())

            animeRemoteSource.getSeasonNow()

            // Then
            verify(jikanService, times(1)).getSeasonNow()
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `When getSeason then jikanService invoked`() {
        runTest {
            // When
            whenever(jikanService.getSeason(2022, "winter")).thenReturn(mock())

            animeRemoteSource.getSeason(2022, "winter")

            // Then
            verify(jikanService, times(1)).getSeason(2022, "winter")
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `When getSeasonList then jikanService invoked`() {
        runTest {
            // When
            whenever(jikanService.getSeasonList()).thenReturn(mock())

            animeRemoteSource.getSeasonList()

            // Then
            verify(jikanService, times(1)).getSeasonList()
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `When getSchedules then jikanService invoked`() {
        runTest {
            // When
            whenever(jikanService.getSchedules("monday")).thenReturn(mock())

            animeRemoteSource.getSchedules("monday")

            // Then
            verify(jikanService, times(1)).getSchedules("monday")
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `When getSearchAnime then jikanService invoked`() {
        runTest {
            // When
            whenever(jikanService.getSearchAnime(1, 1, null, null, null, null, null, null, null, null, null, null, null, null, null, null)).thenReturn(mock())

            animeRemoteSource.getSearchAnime(1, 1, null, null, null, null, null, null, null, null, null, null, null, null, null, null)

            // Then
            verify(jikanService, times(1)).getSearchAnime(1, 1, null, null, null, null, null, null, null, null, null, null, null, null, null, null)
        }
    }
}