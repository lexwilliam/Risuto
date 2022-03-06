package com.lexwilliam.data_remote.mapper

import com.lexwilliam.data.model.remote.user.UserAnimeUpdateRepo
import com.lexwilliam.data_remote.model.user.UserAnimeUpdateResponse
import javax.inject.Inject

interface UserMapper {
    fun toRepo(update: UserAnimeUpdateResponse): UserAnimeUpdateRepo
}

class UserMapperImpl @Inject constructor(): UserMapper {
    override fun toRepo(update: UserAnimeUpdateResponse): UserAnimeUpdateRepo =
        UserAnimeUpdateRepo(update.comments, update.isReWatching, update.numTimesReWatched, update.numWatchedEpisodes, update.priority, update.reWatchValue, update.score, update.status, update.tags, update.updatedAt)

}