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
        UserProfileRepo.Data(profile.about?:"", profile.birthday?:"", profile.external.map { toRepo(it) }, toRepo(profile.favorites), profile.gender?:"", toRepo(profile.images), profile.joined, profile.last_online, profile.location?:"", profile.mal_id, toRepo(profile.statistics), toRepo(profile.updates), profile.url, profile.username)

    private fun toRepo(images: UserProfileResponse.Data.Images): UserProfileRepo.Data.Images =
        UserProfileRepo.Data.Images(toRepo(images.jpg))

    private fun toRepo(jpg: UserProfileResponse.Data.Images.Jpg): UserProfileRepo.Data.Images.Jpg =
        UserProfileRepo.Data.Images.Jpg(jpg.image_url?:"")

    private fun toRepo(statistics: UserProfileResponse.Data.Statistics): UserProfileRepo.Data.Statistics =
        UserProfileRepo.Data.Statistics(toRepo(statistics.anime))

    private fun toRepo(anime: UserProfileResponse.Data.Statistics.Anime): UserProfileRepo.Data.Statistics.Anime =
        UserProfileRepo.Data.Statistics.Anime(anime.completed, anime.days_watched, anime.dropped, anime.episodes_watched, anime.mean_score, anime.on_hold, anime.plan_to_watch, anime.rewatched, anime.total_entries, anime.watching)

    private fun toRepo(external: UserProfileResponse.Data.External): UserProfileRepo.Data.External =
        UserProfileRepo.Data.External(external.name, external.url)

    private fun toRepo(favorites: UserProfileResponse.Data.Favorites): UserProfileRepo.Data.Favorites =
        UserProfileRepo.Data.Favorites(favorites.anime.map { toRepo(it) }, favorites.characters.map { toRepo(it) }, favorites.people.map { toRepo(it) })

    private fun toRepo(anime: UserProfileResponse.Data.Favorites.Anime): UserProfileRepo.Data.Favorites.Anime =
        UserProfileRepo.Data.Favorites.Anime(toRepo(anime.images), anime.mal_id, anime.start_year, anime.title, anime.type, anime.url)

    private fun toRepo(character: UserProfileResponse.Data.Favorites.Character): UserProfileRepo.Data.Favorites.Character =
        UserProfileRepo.Data.Favorites.Character(toRepo(character.images), character.mal_id, character.name, character.url)

    private fun toRepo(person: UserProfileResponse.Data.Favorites.People): UserProfileRepo.Data.Favorites.People =
        UserProfileRepo.Data.Favorites.People(toRepo(person.images), person.mal_id, person.name, person.url)

    private fun toRepo(update: UserProfileResponse.Data.Updates): UserProfileRepo.Data.Updates =
        UserProfileRepo.Data.Updates(update.anime.map { toRepo(it) })

    private fun toRepo(anime: UserProfileResponse.Data.Updates.Anime): UserProfileRepo.Data.Updates.Anime =
        UserProfileRepo.Data.Updates.Anime(anime.date, toRepo(anime.entry), anime.episodes_seen?:-1, anime.episodes_total?:-1, anime.score, anime.status)

    private fun toRepo(entry: UserProfileResponse.Data.Updates.Anime.Entry): UserProfileRepo.Data.Updates.Anime.Entry =
        UserProfileRepo.Data.Updates.Anime.Entry(toRepo(entry.images), entry.mal_id, entry.title, entry.url)
}