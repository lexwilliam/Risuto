package com.lexwilliam.data_remote.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.lexwilliam.MainCoroutineRule
import com.lexwilliam.data.AnimeRemoteSource
import com.lexwilliam.data_remote.JikanService
import com.lexwilliam.data_remote.mapper.AnimeMapper
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@RunWith(MockitoJUnitRunner::class)
class AnimeRemoteSourceImplTest {
    private lateinit var animeRemoteSource: AnimeRemoteSource

    @Mock
    lateinit var jikanService: JikanService

    @Mock
    lateinit var service: JikanService

    @Mock
    lateinit var animeMapper: AnimeMapper

    @Mock
    lateinit var server: MockWebServer

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
        server = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(JikanService::class.java)
    }

    private fun enqueueMockResponse(fileName: String) {
        javaClass.classLoader?.let {
            val inputStream = it.getResourceAsStream(fileName)
            val source = inputStream.source().buffer()
            val mockResponse = MockResponse()
            mockResponse.setBody(source.readString(Charsets.UTF_8))
            server.enqueue(mockResponse)
        }
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

//    @Test
//    fun getSeason_sentRequest_receivedExpected() {
//        runBlocking {
//            // Prepare fake response
//            enqueueMockResponse("GetSeason_Success.json")
//            // Send request to the MockServer
//            val responseBody = service.getSeasonNow()
//            // Request received by the mock server
//            val request = server.takeRequest()
//            assertThat(responseBody).isNotNull()
//            assertThat(request.path).isEqualTo("v4/seasons/2022/spring")
//        }
//    }

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