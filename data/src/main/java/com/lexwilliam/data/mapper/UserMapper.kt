package com.lexwilliam.data.mapper

import com.lexwilliam.data.model.remote.user.UserAnimeUpdateRepo
import com.lexwilliam.data.model.remote.user.UserProfileRepo
import com.lexwilliam.domain.model.remote.user.UserAnimeUpdate
import com.lexwilliam.domain.model.remote.user.UserProfile
import javax.inject.Inject

interface UserMapper {
    fun toDomain(update: UserAnimeUpdateRepo): UserAnimeUpdate
    fun toDomain(profile: UserProfileRepo): UserProfile
}

class UserMapperImpl @Inject constructor(): UserMapper {
    override fun toDomain(update: UserAnimeUpdateRepo): UserAnimeUpdate =
        UserAnimeUpdate(update.comments, update.isReWatching, update.numTimesReWatched, update.numWatchedEpisodes, update.priority, update.reWatchValue, update.score, update.status, update.tags, update.updatedAt)

    override fun toDomain(profile: UserProfileRepo): UserProfile =
        UserProfile(toDomain(profile.data))

    private fun toDomain(profile: UserProfileRepo.Data): UserProfile.Data =
        UserProfile.Data(profile.birthday, profile.gender, toDomain(profile.images), profile.joined, profile.last_online, profile.location, profile.mal_id, profile.url, profile.username)

    private fun toDomain(images: UserProfileRepo.Data.Images): UserProfile.Data.Images =
        UserProfile.Data.Images(toDomain(images.jpg), toDomain(images.webp))

    private fun toDomain(jpg: UserProfileRepo.Data.Images.Jpg): UserProfile.Data.Images.Jpg =
        UserProfile.Data.Images.Jpg(jpg.image_url)

    private fun toDomain(webp: UserProfileRepo.Data.Images.Webp): UserProfile.Data.Images.Webp =
        UserProfile.Data.Images.Webp(webp.image_url?:"")
}