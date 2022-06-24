package com.lexwilliam.domain.model.remote.people

data class Person(
    val `data`: Data
) {
    data class Data(
        val about: String,
        val alternate_names: List<String>,
        val anime: List<Anime>,
        val birthday: String,
        val family_name: String,
        val favorites: Int,
        val given_name: String,
        val images: Images,
        val mal_id: Int,
        val manga: List<Manga>,
        val name: String,
        val url: String,
        val voices: List<Voice>,
        val website_url: String
    ) {
        data class Anime(
            val anime: AnimeX,
            val position: String
        ) {
            data class AnimeX(
                val images: Images,
                val mal_id: Int,
                val title: String,
                val url: String
            )
        }
        data class Images(
            val jpg: Jpg
        ) {
            data class Jpg(
                val image_url: String
            )
        }
        data class Manga(
            val manga: MangaX,
            val position: String
        ) {
            data class MangaX(
                val images: Images,
                val mal_id: Int,
                val title: String,
                val url: String
            )
        }
        data class Voice(
            val anime: Anime,
            val character: Character,
            val role: String
        ) {
            data class Anime(
                val images: Images,
                val mal_id: Int,
                val title: String,
                val url: String
            )
            data class Character(
                val images: Images,
                val mal_id: Int,
                val name: String,
                val url: String
            )
        }
    }
}