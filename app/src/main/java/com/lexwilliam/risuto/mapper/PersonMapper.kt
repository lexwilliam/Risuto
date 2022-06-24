package com.lexwilliam.risuto.mapper

import com.lexwilliam.domain.model.remote.people.Person
import com.lexwilliam.risuto.model.PersonPresentation
import javax.inject.Inject

interface PersonMapper {
    fun toPresentation(person: Person): PersonPresentation
}

class PersonMapperImpl @Inject constructor(): PersonMapper {
    override fun toPresentation(person: Person): PersonPresentation =
        PersonPresentation(toPresentation(person.data))

    private fun toPresentation(data: Person.Data): PersonPresentation.Data =
        PersonPresentation.Data(data.about, data.alternate_names, data.anime.map { toPresentation(it) }, data.birthday, data.family_name, data.favorites, data.given_name, toPresentation(data.images), data.mal_id, data.manga.map { toPresentation(it) }, data.name, data.url, data.voices.map { toPresentation(it) }, data.website_url)

    private fun toPresentation(anime: Person.Data.Anime): PersonPresentation.Data.Anime =
        PersonPresentation.Data.Anime(toPresentation(anime.anime), anime.position)

    private fun toPresentation(animex: Person.Data.Anime.AnimeX): PersonPresentation.Data.Anime.AnimeX =
        PersonPresentation.Data.Anime.AnimeX(toPresentation(animex.images), animex.mal_id, animex.title, animex.url)

    private fun toPresentation(manga: Person.Data.Manga): PersonPresentation.Data.Manga =
        PersonPresentation.Data.Manga(toPresentation(manga.manga), manga.position)

    private fun toPresentation(mangax: Person.Data.Manga.MangaX): PersonPresentation.Data.Manga.MangaX =
        PersonPresentation.Data.Manga.MangaX(toPresentation(mangax.images), mangax.mal_id, mangax.title, mangax.url)

    private fun toPresentation(image: Person.Data.Images): PersonPresentation.Data.Images =
        PersonPresentation.Data.Images(toPresentation(image.jpg))

    private fun toPresentation(jpg: Person.Data.Images.Jpg): PersonPresentation.Data.Images.Jpg =
        PersonPresentation.Data.Images.Jpg(jpg.image_url)

    private fun toPresentation(voice: Person.Data.Voice): PersonPresentation.Data.Voice =
        PersonPresentation.Data.Voice(toPresentation(voice.anime), toPresentation(voice.character), voice.role)

    private fun toPresentation(voiceAnime: Person.Data.Voice.Anime): PersonPresentation.Data.Voice.Anime =
        PersonPresentation.Data.Voice.Anime(toPresentation(voiceAnime.images), voiceAnime.mal_id, voiceAnime.title, voiceAnime.url)

    private fun toPresentation(character: Person.Data.Voice.Character): PersonPresentation.Data.Voice.Character =
        PersonPresentation.Data.Voice.Character(toPresentation(character.images), character.mal_id, character.name, character.url)

}