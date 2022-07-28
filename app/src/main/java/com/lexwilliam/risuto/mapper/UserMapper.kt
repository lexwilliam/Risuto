package com.lexwilliam.risuto.mapper

import com.lexwilliam.data.model.remote.user.UserAnimeUpdateRepo
import com.lexwilliam.data.model.remote.user.UserProfileRepo
import com.lexwilliam.domain.model.remote.user.UserAnimeUpdate
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
        UserProfilePresentation.Data(profile.about, profile.birthday, profile.external.map { toPresentation(it) }, toPresentation(profile.favorites), profile.gender, toPresentation(profile.images), profile.joined, profile.last_online, profile.location, profile.mal_id, toPresentation(profile.statistics), toPresentation(profile.updates), profile.url, profile.username)

    private fun toPresentation(images: UserProfile.Data.Images): UserProfilePresentation.Data.Images =
        UserProfilePresentation.Data.Images(toPresentation(images.jpg))

    private fun toPresentation(jpg: UserProfile.Data.Images.Jpg): UserProfilePresentation.Data.Images.Jpg =
        UserProfilePresentation.Data.Images.Jpg(jpg.image_url)

    private fun toPresentation(statistics: UserProfile.Data.Statistics): UserProfilePresentation.Data.Statistics =
        UserProfilePresentation.Data.Statistics(toPresentation(statistics.anime))

    private fun toPresentation(anime: UserProfile.Data.Statistics.Anime): UserProfilePresentation.Data.Statistics.Anime =
        UserProfilePresentation.Data.Statistics.Anime(anime.completed, anime.days_watched, anime.dropped, anime.episodes_watched, anime.mean_score, anime.on_hold, anime.plan_to_watch, anime.rewatched, anime.total_entries, anime.watching)

    private fun toPresentation(external: UserProfile.Data.External): UserProfilePresentation.Data.External =
        UserProfilePresentation.Data.External(external.name, external.url)

    private fun toPresentation(favorites: UserProfile.Data.Favorites): UserProfilePresentation.Data.Favorites =
        UserProfilePresentation.Data.Favorites(favorites.anime.map { toPresentation(it) }, favorites.characters.map { toPresentation(it) }, favorites.people.map { toPresentation(it) })

    private fun toPresentation(anime: UserProfile.Data.Favorites.Anime): UserProfilePresentation.Data.Favorites.Anime =
        UserProfilePresentation.Data.Favorites.Anime(toPresentation(anime.images), anime.mal_id, anime.start_year, anime.title, anime.type, anime.url)

    private fun toPresentation(character: UserProfile.Data.Favorites.Character): UserProfilePresentation.Data.Favorites.Character =
        UserProfilePresentation.Data.Favorites.Character(toPresentation(character.images), character.mal_id, character.name, character.url)

    private fun toPresentation(person: UserProfile.Data.Favorites.People): UserProfilePresentation.Data.Favorites.People =
        UserProfilePresentation.Data.Favorites.People(toPresentation(person.images), person.mal_id, person.name, person.url)

    private fun toPresentation(update: UserProfile.Data.Updates): UserProfilePresentation.Data.Updates =
        UserProfilePresentation.Data.Updates(update.anime.map { toPresentation(it) })

    private fun toPresentation(anime: UserProfile.Data.Updates.Anime): UserProfilePresentation.Data.Updates.Anime =
        UserProfilePresentation.Data.Updates.Anime(anime.date, toPresentation(anime.entry), anime.episodes_seen, anime.episodes_total, anime.score, anime.status)

    private fun toPresentation(entry: UserProfile.Data.Updates.Anime.Entry): UserProfilePresentation.Data.Updates.Anime.Entry =
        UserProfilePresentation.Data.Updates.Anime.Entry(toPresentation(entry.images), entry.mal_id, entry.title, entry.url)
}