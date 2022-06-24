package com.lexwilliam.data_remote.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.lexwilliam.MainCoroutineRule
import com.lexwilliam.data.PersonRemoteSource
import com.lexwilliam.data.UserRemoteSource
import com.lexwilliam.data_remote.JikanService
import com.lexwilliam.data_remote.MyAnimeListService
import com.lexwilliam.data_remote.mapper.AnimeMapper
import com.lexwilliam.data_remote.mapper.PersonMapper
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
import org.mockito.Mock

class PersonRemoteSourceImplTest {

    private lateinit var personRemoteSource: PersonRemoteSource

    @Mock
    lateinit var jikanService: JikanService

    @Mock
    lateinit var personMapper: PersonMapper

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @JvmField
    @Rule
    val mainCoroutineRule = MainCoroutineRule()

    @FlowPreview
    @Before
    fun setUp() {
        personRemoteSource = PersonRemoteSourceImpl(
            jikanService,
            personMapper
        )
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `When getPersonById then jikanService invoked`() {
        runTest {
            // When
            whenever(jikanService.getPersonFullById(1)).thenReturn(mock())

            personRemoteSource.getPeopleById(1)

            // Then
            verify(jikanService, times(1)).getPersonFullById(1)
        }
    }
}