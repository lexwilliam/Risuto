package com.lexwilliam.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.lexwilliam.MainCoroutineRule
import com.lexwilliam.data.AnimeRemoteSource
import com.lexwilliam.data.mapper.AnimeMapper
import com.nhaarman.mockito_kotlin.*
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
class AnimeRepositoryImplTest {
    private lateinit var animeRepository: AnimeRepositoryImpl

    @Mock
    lateinit var animeRemoteSource: AnimeRemoteSource

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
        animeRepository = AnimeRepositoryImpl(
            animeRemoteSource,
            animeMapper
        )
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `When getTopAnime then animeRemoteSource invoked`() {
        runTest {
            // When
            whenever(animeRemoteSource.getTopAnime()).thenReturn(mock())

            animeRepository.getTopAnime()

            // Then
            verify(animeRemoteSource, times(1)).getTopAnime()
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `When getSeasonNow then animeRemoteSource invoked`() {
        runTest {
            // When
            whenever(animeRemoteSource.getSeasonNow()).thenReturn(mock())

            animeRepository.getSeasonNow()

            // Then
            verify(animeRemoteSource, times(1)).getSeasonNow()
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `When getSeason then animeRemoteSource invoked`() {
        runTest {
            // When
            whenever(animeRemoteSource.getSeason(2022, "winter")).thenReturn(mock())

            animeRepository.getSeason(2022, "winter")

            // Then
            verify(animeRemoteSource, times(1)).getSeason(2022, "winter")
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `When getSeasonList then animeRemoteSource invoked`() {
        runTest {
            // When
            whenever(animeRemoteSource.getSeasonList()).thenReturn(mock())

            animeRepository.getSeasonList()

            // Then
            verify(animeRemoteSource, times(1)).getSeasonList()
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `When getSchedules then animeRemoteSource invoked`() {
        runTest {
            // When
            whenever(animeRemoteSource.getSchedules("monday")).thenReturn(mock())

            animeRepository.getSchedules("monday")

            // Then
            verify(animeRemoteSource, times(1)).getSchedules("monday")
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `When getSearchAnime then animeRemoteSource invoked`() {
        runTest {
            // When
            whenever(animeRemoteSource.getSearchAnime(1, 1, null, null, null, null, null, null, null, null, null, null, null, null, null, null)).thenReturn(mock())

            animeRepository.getSearchAnime(1, 1, null, null, null, null, null, null, null, null, null, null, null, null, null, null)

            // Then
            verify(animeRemoteSource, times(1)).getSearchAnime(1, 1, null, null, null, null, null, null, null, null, null, null, null, null, null, null)
        }
    }
}