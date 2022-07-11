package com.lexwilliam.data_remote.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.lexwilliam.MainCoroutineRule
import com.lexwilliam.data.AnimeRemoteSource
import com.lexwilliam.data.DetailRemoteSource
import com.lexwilliam.data_remote.JikanService
import com.lexwilliam.data_remote.MyAnimeListService
import com.lexwilliam.data_remote.mapper.AnimeMapper
import com.lexwilliam.data_remote.mapper.DetailMapper
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
class DetailRemoteSourceImplTest {
    private lateinit var detailRemoteSource: DetailRemoteSource

    @Mock
    lateinit var malService: MyAnimeListService

    @Mock
    lateinit var jikanService: JikanService

    @Mock
    lateinit var detailMapper: DetailMapper

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @JvmField
    @Rule
    val mainCoroutineRule = MainCoroutineRule()

    @FlowPreview
    @Before
    fun setUp() {
        detailRemoteSource = DetailRemoteSourceImpl(
            malService,
            jikanService,
            detailMapper
        )
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `When getAnimeCharacters then jikanService invoked`() {
        runTest {
            // When
            whenever(jikanService.getAnimeCharacters(1)).thenReturn(mock())

            detailRemoteSource.getAnimeCharacters(1)

            // Then
            verify(jikanService, times(1)).getAnimeCharacters(1)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `When getAnimeVideos then jikanService invoked`() {
        runTest {
            // When
            whenever(jikanService.getAnimeVideos(1)).thenReturn(mock())

            detailRemoteSource.getAnimeVideos(1)

            // Then
            verify(jikanService, times(1)).getAnimeVideos(1)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `When getAnimeStaff then jikanService invoked`() {
        runTest {
            // When
            whenever(jikanService.getAnimeStaff(1)).thenReturn(mock())

            detailRemoteSource.getAnimeStaff(1)

            // Then
            verify(jikanService, times(1)).getAnimeStaff(1)
        }
    }
}