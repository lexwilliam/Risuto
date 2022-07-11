package com.lexwilliam.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.lexwilliam.MainCoroutineRule
import com.lexwilliam.data.HistoryLocalSource
import com.lexwilliam.data.mapper.HistoryMapper
import com.lexwilliam.data.model.local.AnimeHistoryRepo
import com.lexwilliam.data.model.local.SearchHistoryRepo
import com.lexwilliam.domain.model.local.AnimeHistory
import com.lexwilliam.domain.model.local.SearchHistory
import com.lexwilliam.domain.repository.HistoryRepository
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
class HistoryRepositoryImplTest {

    private lateinit var historyRepository: HistoryRepositoryImpl

    @Mock
    lateinit var historyLocalSource: HistoryLocalSource

    @Mock
    lateinit var historyMapper: HistoryMapper

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @JvmField
    @Rule
    val mainCoroutineRule = MainCoroutineRule()

    @FlowPreview
    @Before
    fun setUp() {
        historyRepository = HistoryRepositoryImpl(
            historyLocalSource, historyMapper
        )
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `When getSearchHistory then historyLocalSource invoked`() {
        // When
        whenever(historyLocalSource.getSearchHistory()).thenReturn(mock())

        historyRepository.getSearchHistory()

        // Then
        verify(historyLocalSource, times(1)).getSearchHistory()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `When getAnimeHistory then historyLocalSource invoked`() {
        // When
        whenever(historyLocalSource.getAnimeHistory()).thenReturn(mock())

        historyRepository.getAnimeHistory()

        // Then
        verify(historyLocalSource, times(1)).getAnimeHistory()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `When deleteSearchHistory then historyLocalSource invoked`() {
        runTest {
            // When
            whenever(historyLocalSource.deleteSearchHistory("")).thenReturn(mock())

            historyRepository.deleteSearchHistory("")

            // Then
            verify(historyLocalSource, times(1)).deleteSearchHistory("")
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `When deleteAllSearch then historyLocalSource invoked`() {
        runTest {
            // When
            whenever(historyLocalSource.deleteAllSearch()).thenReturn(mock())

            historyRepository.deleteAllSearch()

            // Then
            verify(historyLocalSource, times(1)).deleteAllSearch()
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `When deleteAnimeByTitle then historyLocalSource invoked`() {
        runTest {
            // When
            whenever(historyLocalSource.deleteAnimeByTitle("")).thenReturn(mock())

            historyRepository.deleteAnimeByTitle("")

            // Then
            verify(historyLocalSource, times(1)).deleteAnimeByTitle("")
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `When deleteAllAnime then historyLocalSource invoked`() {
        runTest {
            // When
            whenever(historyLocalSource.deleteAllAnime()).thenReturn(mock())

            historyRepository.deleteAllAnime()

            // Then
            verify(historyLocalSource, times(1)).deleteAllAnime()
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `When insertSearch then historyLocalSource invoked`() {
        runTest {
            // When
            whenever(historyLocalSource.insertSearch(SearchHistoryRepo(""))).thenReturn(mock())

            historyRepository.insertSearch(SearchHistory(""))

            // Then
            verify(historyLocalSource, times(1)).insertSearch(SearchHistoryRepo(""))
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `When insertAnime then historyLocalSource invoked`() {
        runTest {
            // When
            whenever(historyLocalSource.insertAnime(AnimeHistoryRepo(-1, "", "", 0L))).thenReturn(mock())

            historyRepository.insertAnime(AnimeHistory(-1, "", "", 0L))

            // Then
            verify(historyLocalSource, times(1)).insertAnime(AnimeHistoryRepo(-1, "", "", 0L))
        }
    }
}