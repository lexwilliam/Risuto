package com.lexwilliam.data_remote.mapper

import android.media.Image
import com.lexwilliam.data.model.remote.people.PersonRepo
import com.lexwilliam.data_remote.model.people.PersonResponse
import javax.inject.Inject

interface PersonMapper {
    fun toRepo(person: PersonResponse): PersonRepo
}

class PersonMapperImpl @Inject constructor(): PersonMapper {
    override fun toRepo(person: PersonResponse): PersonRepo =
        PersonRepo(toRepo(person.data))

    private fun toRepo(data: PersonResponse.Data): PersonRepo.Data =
        PersonRepo.Data(data.about?:"", data.alternate_names?: emptyList(), data.anime.map { toRepo(it) }, data.birthday?:"", data.family_name?:"", data.favorites?:-1, data.given_name?:"", toRepo(data.images?:PersonResponse.Data.Images(PersonResponse.Data.Images.Jpg(""))), data.mal_id, data.manga?.map { toRepo(it) }?: emptyList(), data.name?:"", data.url?:"", data.voices?.map { toRepo(it) }?: emptyList(), data.website_url?:"")

    private fun toRepo(anime: PersonResponse.Data.Anime): PersonRepo.Data.Anime =
        PersonRepo.Data.Anime(toRepo(anime.anime), anime.position)

    private fun toRepo(animex: PersonResponse.Data.Anime.AnimeX): PersonRepo.Data.Anime.AnimeX =
        PersonRepo.Data.Anime.AnimeX(toRepo(animex.images), animex.mal_id, animex.title, animex.url)

    private fun toRepo(manga: PersonResponse.Data.Manga): PersonRepo.Data.Manga =
        PersonRepo.Data.Manga(toRepo(manga.manga), manga.position)

    private fun toRepo(mangax: PersonResponse.Data.Manga.MangaX): PersonRepo.Data.Manga.MangaX =
        PersonRepo.Data.Manga.MangaX(toRepo(mangax.images), mangax.mal_id, mangax.title, mangax.url)

    private fun toRepo(image: PersonResponse.Data.Images): PersonRepo.Data.Images =
        PersonRepo.Data.Images(toRepo(image.jpg))

    private fun toRepo(jpg: PersonResponse.Data.Images.Jpg): PersonRepo.Data.Images.Jpg =
        PersonRepo.Data.Images.Jpg(jpg.image_url)

    private fun toRepo(voice: PersonResponse.Data.Voice): PersonRepo.Data.Voice =
        PersonRepo.Data.Voice(toRepo(voice.anime), toRepo(voice.character), voice.role)

    private fun toRepo(voiceAnime: PersonResponse.Data.Voice.Anime): PersonRepo.Data.Voice.Anime =
        PersonRepo.Data.Voice.Anime(toRepo(voiceAnime.images), voiceAnime.mal_id, voiceAnime.title, voiceAnime.url)

    private fun toRepo(character: PersonResponse.Data.Voice.Character): PersonRepo.Data.Voice.Character =
        PersonRepo.Data.Voice.Character(toRepo(character.images), character.mal_id, character.name, character.url)

}