package com.lexwilliam.data_remote.mapper

import com.lexwilliam.data.model.remote.character.CharacterDetailRepo
import com.lexwilliam.data_remote.model.character.CharacterDetailResponse
import javax.inject.Inject

interface CharacterMapper {
    fun toRepo(char: CharacterDetailResponse): CharacterDetailRepo
}

class CharacterMapperImpl @Inject constructor(): CharacterMapper {

    override fun toRepo(char: CharacterDetailResponse): CharacterDetailRepo =
        CharacterDetailRepo(toRepo(char.data))

    private fun toRepo(data: CharacterDetailResponse.Data): CharacterDetailRepo.Data =
        CharacterDetailRepo.Data(data.about?:"", data.anime.map { toRepo(it) }, data.favorites?:-1, data.mal_id?:-1, data.name?:"", data.name_kanji?:"", data.url?:"", data.voices.map { toRepo(it) })

    private fun toRepo(anime: CharacterDetailResponse.Data.Anime): CharacterDetailRepo.Data.Anime =
        CharacterDetailRepo.Data.Anime(toRepo(anime.anime), anime.role?:"")

    private fun toRepo(data: CharacterDetailResponse.Data.Anime.Data): CharacterDetailRepo.Data.Anime.Data =
        CharacterDetailRepo.Data.Anime.Data(toRepo(data.images), data.mal_id?:-1, data.title?:"", data.url?:"")

    private fun toRepo(images: CharacterDetailResponse.Data.Images): CharacterDetailRepo.Data.Images =
        CharacterDetailRepo.Data.Images(toRepo(images.jpg))

    private fun toRepo(jpg: CharacterDetailResponse.Data.Images.Jpg): CharacterDetailRepo.Data.Images.Jpg =
        CharacterDetailRepo.Data.Images.Jpg(jpg.image_url?:"")

    private fun toRepo(voice: CharacterDetailResponse.Data.Voice): CharacterDetailRepo.Data.Voice =
        CharacterDetailRepo.Data.Voice(voice.language?:"", toRepo(voice.person))

    private fun toRepo(person: CharacterDetailResponse.Data.Voice.Person): CharacterDetailRepo.Data.Voice.Person =
        CharacterDetailRepo.Data.Voice.Person(toRepo(person.images), person.mal_id?:-1, person.name?:"", person.url?:"")
}