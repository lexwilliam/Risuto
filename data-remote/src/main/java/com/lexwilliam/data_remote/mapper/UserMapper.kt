package com.lexwilliam.data_remote.mapper

import com.lexwilliam.data.model.remote.user.UserAnimeUpdateRepo
import com.lexwilliam.data.model.remote.user.UserProfileRepo
import com.lexwilliam.data_remote.model.user.UserAnimeUpdateResponse
import com.lexwilliam.data_remote.model.user.UserProfileResponse
import javax.inject.Inject

interface UserMapper {
    fun toRepo(update: UserAnimeUpdateResponse): UserAnimeUpdateRepo
    fun toRepo(profile: UserProfileResponse): UserProfileRepo
}

class UserMapperImpl @Inject constructor(): UserMapper {
    override fun toRepo(update: UserAnimeUpdateResponse): UserAnimeUpdateRepo =
        UserAnimeUpdateRepo(update.comments, update.isReWatching, update.numTimesReWatched, update.numWatchedEpisodes, update.priority, update.reWatchValue, update.score, update.status, update.tags, update.updatedAt)

    override fun toRepo(profile: UserProfileResponse): UserProfileRepo =
        UserProfileRepo(toRepo(profile.data))

    private fun toRepo(profile: UserProfileResponse.Data): UserProfileRepo.Data =
        UserProfileRepo.Data(profile.birthday?:"", profile.gender?:"", toRepo(profile.images?: UserProfileResponse.Data.Images(UserProfileResponse.Data.Images.Jpg(""), UserProfileResponse.Data.Images.Webp(""))), profile.joined, profile.last_online, profile.location?:"", profile.mal_id, profile.url, profile.username, toRepo(profile.statistics))

    private fun toRepo(images: UserProfileResponse.Data.Images): UserProfileRepo.Data.Images =
        UserProfileRepo.Data.Images(toRepo(images.jpg), toRepo(images.webp))

    private fun toRepo(jpg: UserProfileResponse.Data.Images.Jpg): UserProfileRepo.Data.Images.Jpg =
        UserProfileRepo.Data.Images.Jpg(jpg.image_url?:"")

    private fun toRepo(webp: UserProfileResponse.Data.Images.Webp): UserProfileRepo.Data.Images.Webp =
        UserProfileRepo.Data.Images.Webp(webp.image_url?:"")

    private fun toRepo(statistics: UserProfileResponse.Data.Statistics): UserProfileRepo.Data.Statistics =
        UserProfileRepo.Data.Statistics(toRepo(statistics.anime), toRepo(statistics.manga))

    private fun toRepo(anime: UserProfileResponse.Data.Statistics.Anime): UserProfileRepo.Data.Statistics.Anime =
        UserProfileRepo.Data.Statistics.Anime(anime.days_watched, anime.mean_score, anime.watching, anime.completed, anime.on_hold, anime.dropped, anime.plan_to_watch, anime.total_entries, anime.rewatched, anime.episodes_watched)

    private fun toRepo(manga: UserProfileResponse.Data.Statistics.Manga): UserProfileRepo.Data.Statistics.Manga =
        UserProfileRepo.Data.Statistics.Manga(manga.days_read, manga.mean_score, manga.reading, manga.completed, manga.on_hold, manga.dropped, manga.plan_to_read, manga.total_entries, manga.reread, manga.chapters_read, manga.volumes_read)

}