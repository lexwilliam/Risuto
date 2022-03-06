package com.lexwilliam.data.mapper

import com.lexwilliam.data.model.remote.user.UserAnimeUpdateRepo
import com.lexwilliam.domain.model.remote.user.UserAnimeUpdate
import javax.inject.Inject

interface UserMapper {
    fun toDomain(update: UserAnimeUpdateRepo): UserAnimeUpdate
}

class UserMapperImpl @Inject constructor(): UserMapper {
    override fun toDomain(update: UserAnimeUpdateRepo): UserAnimeUpdate =
        UserAnimeUpdate(update.comments, update.isReWatching, update.numTimesReWatched, update.numWatchedEpisodes, update.priority, update.reWatchValue, update.score, update.status, update.tags, update.updatedAt)

}