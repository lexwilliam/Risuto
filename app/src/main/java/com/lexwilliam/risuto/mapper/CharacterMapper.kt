package com.lexwilliam.risuto.mapper

import com.lexwilliam.domain.model.remote.character.CharacterDetail
import com.lexwilliam.risuto.model.CharacterDetailPresentation
import javax.inject.Inject

interface CharacterMapper {
    fun toPresentation(char: CharacterDetail): CharacterDetailPresentation
}

class CharacterMapperImpl @Inject constructor(): CharacterMapper {

    override fun toPresentation(char: CharacterDetail): CharacterDetailPresentation =
        CharacterDetailPresentation(toPresentation(char.data))

    private fun toPresentation(data: CharacterDetail.Data): CharacterDetailPresentation.Data =
        CharacterDetailPresentation.Data(data.about, data.anime.map { toPresentation(it) }, data.favorites, data.mal_id, data.name, data.name_kanji, data.url, data.voices.map { toPresentation(it) })

    private fun toPresentation(anime: CharacterDetail.Data.Anime): CharacterDetailPresentation.Data.Anime =
        CharacterDetailPresentation.Data.Anime(toPresentation(anime.data), anime.role)

    private fun toPresentation(data: CharacterDetail.Data.Anime.Data): CharacterDetailPresentation.Data.Anime.Data =
        CharacterDetailPresentation.Data.Anime.Data(toPresentation(data.images), data.mal_id, data.title, data.url)

    private fun toPresentation(images: CharacterDetail.Data.Images): CharacterDetailPresentation.Data.Images =
        CharacterDetailPresentation.Data.Images(toPresentation(images.jpg))

    private fun toPresentation(jpg: CharacterDetail.Data.Images.Jpg): CharacterDetailPresentation.Data.Images.Jpg =
        CharacterDetailPresentation.Data.Images.Jpg(jpg.image_url)

    private fun toPresentation(voice: CharacterDetail.Data.Voice): CharacterDetailPresentation.Data.Voice =
        CharacterDetailPresentation.Data.Voice(voice.language, toPresentation(voice.person))

    private fun toPresentation(person: CharacterDetail.Data.Voice.Person): CharacterDetailPresentation.Data.Voice.Person =
        CharacterDetailPresentation.Data.Voice.Person(toPresentation(person.images), person.mal_id, person.name, person.url)
}