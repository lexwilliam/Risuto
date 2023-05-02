package com.lexwilliam.data.mapper

import com.lexwilliam.data.model.remote.character.CharacterDetailRepo
import com.lexwilliam.domain.model.remote.character.CharacterDetail
import javax.inject.Inject

interface CharacterMapper {
    fun toDomain(char: CharacterDetailRepo): CharacterDetail
}

class CharacterMapperImpl @Inject constructor(): CharacterMapper {

    override fun toDomain(char: CharacterDetailRepo): CharacterDetail =
        CharacterDetail(toDomain(char.data))

    private fun toDomain(data: CharacterDetailRepo.Data): CharacterDetail.Data =
        CharacterDetail.Data(data.about, data.anime.map { toDomain(it) }, data.favorites, data.mal_id, data.name, data.name_kanji, data.url, data.voices.map { toDomain(it) })

    private fun toDomain(anime: CharacterDetailRepo.Data.Anime): CharacterDetail.Data.Anime =
        CharacterDetail.Data.Anime(toDomain(anime.data), anime.role)

    private fun toDomain(data: CharacterDetailRepo.Data.Anime.Data): CharacterDetail.Data.Anime.Data =
        CharacterDetail.Data.Anime.Data(toDomain(data.images), data.mal_id, data.title, data.url)

    private fun toDomain(images: CharacterDetailRepo.Data.Images): CharacterDetail.Data.Images =
        CharacterDetail.Data.Images(toDomain(images.jpg))

    private fun toDomain(jpg: CharacterDetailRepo.Data.Images.Jpg): CharacterDetail.Data.Images.Jpg =
        CharacterDetail.Data.Images.Jpg(jpg.image_url)

    private fun toDomain(voice: CharacterDetailRepo.Data.Voice): CharacterDetail.Data.Voice =
        CharacterDetail.Data.Voice(voice.language, toDomain(voice.person))

    private fun toDomain(person: CharacterDetailRepo.Data.Voice.Person): CharacterDetail.Data.Voice.Person =
        CharacterDetail.Data.Voice.Person(toDomain(person.images), person.mal_id, person.name, person.url)
}