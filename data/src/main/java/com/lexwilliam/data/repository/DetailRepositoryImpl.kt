package com.lexwilliam.data.repository

import com.lexwilliam.data.DetailRemoteSource
import com.lexwilliam.data.OAuthLocalSource
import com.lexwilliam.data.constant.ApiConstant
import com.lexwilliam.data.mapper.DetailMapper
import com.lexwilliam.domain.model.remote.anime.AnimeDetail
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

}