package com.lexwilliam.domain.model.remote.character

data class CharacterDetail(
    val `data`: Data
) {
    data class Data(
        val about: String,
        val anime: List<Anime>,
        val favorites: Int,
        val mal_id: Int,
        val name: String,
        val name_kanji: String,
        val url: String,
        val voices: List<Voice>
    ) {
        data class Images(
            val jpg: Jpg
        ) {
            data class Jpg(
                val image_url: String
            )
        }
        data class Anime(
            val data: Data,
            val role: String
        ) {
            data class Data(
                val images: Images,
                val mal_id: Int,
                val title: String,
                val url: String
            )
        }
        data class Voice(
            val language: String,
            val person: Person
        ) {
            data class Person(
                val images: Images,
                val mal_id: Int,
                val name: String,
                val url: String
            )
        }
    }
}