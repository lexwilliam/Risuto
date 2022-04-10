package com.lexwilliam.data.repository

import com.lexwilliam.data.DetailRemoteSource
import com.lexwilliam.data.OAuthLocalSource
import com.lexwilliam.data.constant.ApiConstant
import com.lexwilliam.data.mapper.DetailMapper
import com.lexwilliam.domain.model.remote.anime.AnimeCharacters
import com.lexwilliam.domain.model.remote.anime.AnimeDetail
import com.lexwilliam.domain.model.remote.anime.AnimeStaff
import com.lexwilliam.domain.model.remote.anime.AnimeVideos
import com.lexwilliam.domain.repository.DetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(
    private val detailRemoteSource: DetailRemoteSource,
    private val oAuthLocalSource: OAuthLocalSource,
    private val detailMapper: DetailMapper
): DetailRepository {
    override suspend fun getAnimeDetails(id: Int): Flow<AnimeDetail> {
        val accessToken = oAuthLocalSource.accessTokenFlow.firstOrNull()
        return detailRemoteSource.getAnimeDetails(ApiConstant.BEARER_SEPARATOR + accessToken, id).map { detailMapper.toDomain(it) }
    }

    override suspend fun getAnimeCharacters(id: Int): Flow<AnimeCharacters> {
        return detailRemoteSource.getAnimeCharacters(id).map { detailMapper.toDomain(it) }
    }

    override suspend fun getAnimeVideos(id: Int): Flow<AnimeVideos> {
        return detailRemoteSource.getAnimeVideos(id).map { detailMapper.toDomain(it) }
    }

    override suspend fun getAnimeStaff(id: Int): Flow<AnimeStaff> {
        return detailRemoteSource.getAnimeStaff(id).map { detailMapper.toDomain(it) }
    }

}