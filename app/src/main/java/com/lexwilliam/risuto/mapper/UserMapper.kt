package com.lexwilliam.risuto.mapper

import com.lexwilliam.domain.model.remote.user.UserProfile
import com.lexwilliam.risuto.model.UserProfilePresentation
import javax.inject.Inject

interface UserMapper {
    fun toPresentation(profile: UserProfile): UserProfilePresentation
}

class UserMapperImpl @Inject constructor(): UserMapper {

    override fun toPresentation(profile: UserProfile): UserProfilePresentation =
        UserProfilePresentation(toPresentation(profile.data))

    private fun toPresentation(profile: UserProfile.Data): UserProfilePresentation.Data =
        UserProfilePresentation.Data(profile.birthday, profile.gender, toPresentation(profile.images), profile.joined, profile.last_online, profile.location, profile.mal_id, profile.url, profile.username, toPresentation(profile.statistics))

    private fun toPresentation(images: UserProfile.Data.Images): UserProfilePresentation.Data.Images =
        UserProfilePresentation.Data.Images(toPresentation(images.jpg), toPresentation(images.webp))

    private fun toPresentation(jpg: UserProfile.Data.Images.Jpg): UserProfilePresentation.Data.Images.Jpg =
        UserProfilePresentation.Data.Images.Jpg(jpg.image_url)

    private fun toPresentation(webp: UserProfile.Data.Images.Webp): UserProfilePresentation.Data.Images.Webp =
        UserProfilePresentation.Data.Images.Webp(webp.image_url)

    private fun toPresentation(statistics: UserProfile.Data.Statistics): UserProfilePresentation.Data.Statistics =
        UserProfilePresentation.Data.Statistics(toPresentation(statistics.anime), toPresentation(statistics.manga))

    private fun toPresentation(anime: UserProfile.Data.Statistics.Anime): UserProfilePresentation.Data.Statistics.Anime =
        UserProfilePresentation.Data.Statistics.Anime(anime.days_watched, anime.mean_score, anime.watching, anime.completed, anime.on_hold, anime.dropped, anime.plan_to_watch, anime.total_entries, anime.rewatched, anime.episodes_watched)

    private fun toPresentation(manga: UserProfile.Data.Statistics.Manga): UserProfilePresentation.Data.Statistics.Manga =
        UserProfilePresentation.Data.Statistics.Manga(manga.days_read, manga.mean_score, manga.reading, manga.completed, manga.on_hold, manga.dropped, manga.plan_to_read, manga.total_entries, manga.reread, manga.chapters_read, manga.volumes_read)
}