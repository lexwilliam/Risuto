package com.lexwilliam.data_remote.data

import com.lexwilliam.data.DetailRemoteSource
import com.lexwilliam.data.model.remote.anime.AnimeCharactersRepo
import com.lexwilliam.data.model.remote.anime.AnimeDetailRepo
import com.lexwilliam.data.model.remote.anime.AnimeStaffRepo
import com.lexwilliam.data.model.remote.anime.AnimeVideosRepo
import com.lexwilliam.data_remote.JikanService
import com.lexwilliam.data_remote.MyAnimeListService
import com.lexwilliam.data_remote.mapper.DetailMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject

class DetailRemoteSourceImpl @Inject constructor(
    private val malService: MyAnimeListService,
    private val jikanService: JikanService,
    private val detailMapper: DetailMapper
): DetailRemoteSource {
    private val _detailSharedFlow = MutableStateFlow(getInitialStateAnimeDetails())
    private val detailSharedFlow = _detailSharedFlow.asSharedFlow()
    private val _charactersSharedFlow = MutableStateFlow(getInitialStateAnimeCharacters())
    private val charactersSharedFlow = _charactersSharedFlow.asSharedFlow()
    private val _videosSharedFlow = MutableStateFlow(getInitialStateAnimeVideos())
    private val videosSharedFlow = _videosSharedFlow.asSharedFlow()
    private val _staffSharedFlow = MutableStateFlow(getInitialStateAnimeStaff())
    private val staffSharedFlow = _staffSharedFlow.asSharedFlow()

    override suspend fun getAnimeDetails(authHeader: String, id: Int): Flow<AnimeDetailRepo> {
        try {
            detailMapper.toRepo(malService.getAnimeDetails(authHeader, id).body()!!)
                .let {
                    _detailSharedFlow.emit(it)
                }
        } catch (connectionException: java.net.UnknownHostException) {
            throw connectionException
        }
        return detailSharedFlow.distinctUntilChanged()
    }

    override suspend fun getAnimeCharacters(id: Int): Flow<AnimeCharactersRepo> {
        try {
            detailMapper.toRepo(jikanService.getAnimeCharacters(id))
                .let {
                    _charactersSharedFlow.emit(it)
                }
        } catch (connectionException: java.net.UnknownHostException) {
            throw connectionException
        }
        return charactersSharedFlow.distinctUntilChanged()
    }

    override suspend fun getAnimeVideos(id: Int): Flow<AnimeVideosRepo> {
        try {
            detailMapper.toRepo(jikanService.getAnimeVideos(id))
                .let {
                    _videosSharedFlow.emit(it)
                }
        } catch (connectionException: java.net.UnknownHostException) {
            throw connectionException
        }
        return videosSharedFlow.distinctUntilChanged()
    }

    override suspend fun getAnimeStaff(id: Int): Flow<AnimeStaffRepo> {
        try {
            detailMapper.toRepo(jikanService.getAnimeStaff(id))
                .let {
                    _staffSharedFlow.emit(it)
                }
        } catch (connectionException: java.net.UnknownHostException) {
            throw connectionException
        }
        return staffSharedFlow.distinctUntilChanged()
    }

    private fun getInitialStateAnimeDetails() =
        AnimeDetailRepo(
            AnimeDetailRepo.AlternativeTitles("", "", emptyList()), -1,
            "", AnimeDetailRepo.Broadcast("", ""), "", "", emptyList(),
            -1, AnimeDetailRepo.MainPicture("", ""), -1.0, "", AnimeDetailRepo.MyListStatus(false, -1, -1, "", ""),
            "", -1, -1, -1, emptyList(), -1, -1, "", emptyList(),
            emptyList(), emptyList(), "", "", AnimeDetailRepo.StartSeason("", -1), AnimeDetailRepo.Statistics(-1, AnimeDetailRepo.Status("", "", "", "", "")),
            "", emptyList(), "", "", "")

    private fun getInitialStateAnimeCharacters() =
        AnimeCharactersRepo(emptyList())

    private fun getInitialStateAnimeVideos() =
        AnimeVideosRepo(AnimeVideosRepo.Data(emptyList(), emptyList()))

    private fun getInitialStateAnimeStaff() =
        AnimeStaffRepo(emptyList())
}