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
        UserProfile.Data(profile.about, profile.birthday, profile.external.map { toDomain(it) }, toDomain(profile.favorites), profile.gender, toDomain(profile.images), profile.joined, profile.last_online, profile.location, profile.mal_id, toDomain(profile.statistics), toDomain(profile.updates), profile.url, profile.username)

    private fun toDomain(images: UserProfileRepo.Data.Images): UserProfile.Data.Images =
        UserProfile.Data.Images(toDomain(images.jpg))

    private fun toDomain(jpg: UserProfileRepo.Data.Images.Jpg): UserProfile.Data.Images.Jpg =
        UserProfile.Data.Images.Jpg(jpg.image_url)

    private fun toDomain(statistics: UserProfileRepo.Data.Statistics): UserProfile.Data.Statistics =
        UserProfile.Data.Statistics(toDomain(statistics.anime))

    private fun toDomain(anime: UserProfileRepo.Data.Statistics.Anime): UserProfile.Data.Statistics.Anime =
        UserProfile.Data.Statistics.Anime(anime.completed, anime.days_watched, anime.dropped, anime.episodes_watched, anime.mean_score, anime.on_hold, anime.plan_to_watch, anime.rewatched, anime.total_entries, anime.watching)

    private fun toDomain(external: UserProfileRepo.Data.External): UserProfile.Data.External =
        UserProfile.Data.External(external.name, external.url)

    private fun toDomain(favorites: UserProfileRepo.Data.Favorites): UserProfile.Data.Favorites =
        UserProfile.Data.Favorites(favorites.anime.map { toDomain(it) }, favorites.characters.map { toDomain(it) }, favorites.people.map { toDomain(it) })

    private fun toDomain(anime: UserProfileRepo.Data.Favorites.Anime): UserProfile.Data.Favorites.Anime =
        UserProfile.Data.Favorites.Anime(toDomain(anime.images), anime.mal_id, anime.start_year, anime.title, anime.type, anime.url)

    private fun toDomain(character: UserProfileRepo.Data.Favorites.Character): UserProfile.Data.Favorites.Character =
        UserProfile.Data.Favorites.Character(toDomain(character.images), character.mal_id, character.name, character.url)

    private fun toDomain(person: UserProfileRepo.Data.Favorites.People): UserProfile.Data.Favorites.People =
        UserProfile.Data.Favorites.People(toDomain(person.images), person.mal_id, person.name, person.url)

    private fun toDomain(update: UserProfileRepo.Data.Updates): UserProfile.Data.Updates =
        UserProfile.Data.Updates(update.anime.map { toDomain(it) })

    private fun toDomain(anime: UserProfileRepo.Data.Updates.Anime): UserProfile.Data.Updates.Anime =
        UserProfile.Data.Updates.Anime(anime.date, toDomain(anime.entry), anime.episodes_seen, anime.episodes_total, anime.score, anime.status)

    private fun toDomain(entry: UserProfileRepo.Data.Updates.Anime.Entry): UserProfile.Data.Updates.Anime.Entry =
        UserProfile.Data.Updates.Anime.Entry(toDomain(entry.images), entry.mal_id, entry.title, entry.url)
}