package com.lexwilliam.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.lexwilliam.MainCoroutineRule
import com.lexwilliam.data.AnimeRemoteSource
import com.lexwilliam.data.mapper.AnimeMapper
import com.nhaarman.mockito_kotlin.atLeast
import com.nhaarman.mockito_kotlin.mock
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
            verify(animeRemoteSource, atLeast(1)).getTopAnime()
        }
    }
}