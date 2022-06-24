package com.lexwilliam.data.mapper

import com.lexwilliam.data.model.remote.people.PersonRepo
import com.lexwilliam.domain.model.remote.people.Person
import javax.inject.Inject

interface PersonMapper {
    fun toDomain(person: PersonRepo): Person
}

class PersonMapperImpl @Inject constructor(): PersonMapper {
    override fun toDomain(person: PersonRepo): Person =
        Person(toDomain(person.data))

    private fun toDomain(data: PersonRepo.Data): Person.Data =
        Person.Data(data.about, data.alternate_names, data.anime.map { toDomain(it) }, data.birthday, data.family_name, data.favorites, data.given_name, toDomain(data.images), data.mal_id, data.manga.map { toDomain(it) }, data.name, data.url, data.voices.map { toDomain(it) }, data.website_url)

    private fun toDomain(anime: PersonRepo.Data.Anime): Person.Data.Anime =
        Person.Data.Anime(toDomain(anime.anime), anime.position)

    private fun toDomain(animex: PersonRepo.Data.Anime.AnimeX): Person.Data.Anime.AnimeX =
        Person.Data.Anime.AnimeX(toDomain(animex.images), animex.mal_id, animex.title, animex.url)

    private fun toDomain(manga: PersonRepo.Data.Manga): Person.Data.Manga =
        Person.Data.Manga(toDomain(manga.manga), manga.position)

    private fun toDomain(mangax: PersonRepo.Data.Manga.MangaX): Person.Data.Manga.MangaX =
        Person.Data.Manga.MangaX(toDomain(mangax.images), mangax.mal_id, mangax.title, mangax.url)

    private fun toDomain(image: PersonRepo.Data.Images): Person.Data.Images =
        Person.Data.Images(toDomain(image.jpg))

    private fun toDomain(jpg: PersonRepo.Data.Images.Jpg): Person.Data.Images.Jpg =
        Person.Data.Images.Jpg(jpg.image_url)

    private fun toDomain(voice: PersonRepo.Data.Voice): Person.Data.Voice =
        Person.Data.Voice(toDomain(voice.anime), toDomain(voice.character), voice.role)

    private fun toDomain(voiceAnime: PersonRepo.Data.Voice.Anime): Person.Data.Voice.Anime =
        Person.Data.Voice.Anime(toDomain(voiceAnime.images), voiceAnime.mal_id, voiceAnime.title, voiceAnime.url)

    private fun toDomain(character: PersonRepo.Data.Voice.Character): Person.Data.Voice.Character =
        Person.Data.Voice.Character(toDomain(character.images), character.mal_id, character.name, character.url)

}