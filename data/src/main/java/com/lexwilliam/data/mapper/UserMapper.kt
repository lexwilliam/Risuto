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
        UserProfile.Data(profile.birthday, profile.gender, toDomain(profile.images), profile.joined, profile.last_online, profile.location, profile.mal_id, profile.url, profile.username, toDomain(profile.statistics))

    private fun toDomain(images: UserProfileRepo.Data.Images): UserProfile.Data.Images =
        UserProfile.Data.Images(toDomain(images.jpg), toDomain(images.webp))

    private fun toDomain(jpg: UserProfileRepo.Data.Images.Jpg): UserProfile.Data.Images.Jpg =
        UserProfile.Data.Images.Jpg(jpg.image_url)

    private fun toDomain(webp: UserProfileRepo.Data.Images.Webp): UserProfile.Data.Images.Webp =
        UserProfile.Data.Images.Webp(webp.image_url)

    private fun toDomain(statistics: UserProfileRepo.Data.Statistics): UserProfile.Data.Statistics =
        UserProfile.Data.Statistics(toDomain(statistics.anime), toDomain(statistics.manga))

    private fun toDomain(anime: UserProfileRepo.Data.Statistics.Anime): UserProfile.Data.Statistics.Anime =
        UserProfile.Data.Statistics.Anime(anime.days_watched, anime.mean_score, anime.watching, anime.completed, anime.on_hold, anime.dropped, anime.plan_to_watch, anime.total_entries, anime.rewatched, anime.episodes_watched)

    private fun toDomain(manga: UserProfileRepo.Data.Statistics.Manga): UserProfile.Data.Statistics.Manga =
        UserProfile.Data.Statistics.Manga(manga.days_read, manga.mean_score, manga.reading, manga.completed, manga.on_hold, manga.dropped, manga.plan_to_read, manga.total_entries, manga.reread, manga.chapters_read, manga.volumes_read)
}