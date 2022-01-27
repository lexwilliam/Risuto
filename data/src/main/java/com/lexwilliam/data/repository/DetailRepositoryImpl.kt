package com.lexwilliam.data.repository

import com.lexwilliam.data.DetailRemoteSource
import com.lexwilliam.data.mapper.DetailMapper
import com.lexwilliam.data.model.remote.detail.Pictures
import com.lexwilliam.domain.model.remote.detail.*
import com.lexwilliam.domain.repository.DetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(
    private val detailRemoteSource: DetailRemoteSource,
    private val detailMapper: DetailMapper
): DetailRepository {
    override suspend fun anime(id: Int): Flow<AnimeDetail> {
        return detailRemoteSource.anime(id).map { detailMapper.toDomain(it) }
    }

    override suspend fun characterStaff(id: Int): Flow<CharacterStaff> {
        return detailRemoteSource.characterStaff(id).map { detailMapper.toDomain(it) }
    }

    override suspend fun episodes(id: Int): Flow<Episodes> {
        return detailRemoteSource.episodes(id).map { detailMapper.toDomain(it) }
    }

    override suspend fun forum(id: Int): Flow<Forum> {
        return detailRemoteSource.forum(id).map { detailMapper.toDomain(it) }
    }

    override suspend fun moreInfo(id: Int): Flow<MoreInfo> {
        return detailRemoteSource.moreInfo(id).map { detailMapper.toDomain(it) }
    }

    override suspend fun news(id: Int): Flow<News> {
        return detailRemoteSource.news(id).map { detailMapper.toDomain(it) }
    }

    override suspend fun pictures(id: Int): Flow<Pictures> {
        return detailRemoteSource.pictures(id).map { detailMapper.toDomain(it) }
    }

    override suspend fun recommendations(id: Int): Flow<Recommendations> {
        return detailRemoteSource.recommendations(id).map { detailMapper.toDomain(it) }
    }

    override suspend fun reviews(id: Int): Flow<Reviews> {
        return detailRemoteSource.reviews(id).map { detailMapper.toDomain(it) }
    }

    override suspend fun stats(id: Int): Flow<Stats> {
        return detailRemoteSource.stats(id).map { detailMapper.toDomain(it) }
    }

    override suspend fun videos(id: Int): Flow<Videos> {
        return detailRemoteSource.videos(id).map { detailMapper.toDomain(it) }
    }

}