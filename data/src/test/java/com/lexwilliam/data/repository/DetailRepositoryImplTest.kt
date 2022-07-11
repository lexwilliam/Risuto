package com.lexwilliam.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.lexwilliam.MainCoroutineRule
import com.lexwilliam.data.DetailRemoteSource
import com.lexwilliam.data.OAuthLocalSource
import com.lexwilliam.data.mapper.DetailMapper
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
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailRepositoryImplTest {
    private lateinit var detailRepository: DetailRepositoryImpl

    @Mock
    lateinit var detailRemoteSource: DetailRemoteSource

    @Mock
    lateinit var oAuthLocalSource: OAuthLocalSource

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
        detailRepository = DetailRepositoryImpl(
            detailRemoteSource, oAuthLocalSource, detailMapper
        )
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `When getAnimeCharacters then detailRemoteSource invoked`() {
        runTest {
            // When
            whenever(detailRemoteSource.getAnimeCharacters(1)).thenReturn(mock())

            detailRepository.getAnimeCharacters(1)

            // Then
            verify(detailRemoteSource, times(1)).getAnimeCharacters(1)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `When getAnimeVideos then detailRemoteSource invoked`() {
        runTest {
            // When
            whenever(detailRemoteSource.getAnimeVideos(1)).thenReturn(mock())

            detailRepository.getAnimeVideos(1)

            // Then
            verify(detailRemoteSource, times(1)).getAnimeVideos(1)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `When getAnimeStaff then detailRemoteSource invoked`() {
        runTest {
            // When
            whenever(detailRemoteSource.getAnimeStaff(1)).thenReturn(mock())

            detailRepository.getAnimeStaff(1)

            // Then
            verify(detailRemoteSource, times(1)).getAnimeStaff(1)
        }
    }
}