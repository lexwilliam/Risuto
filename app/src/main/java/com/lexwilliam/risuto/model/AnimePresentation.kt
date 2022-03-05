package com.lexwilliam.risuto.model

data class AnimePresentation(
    val `data`: List<Data>,
    val pagination: Pagination
) {
    data class Pagination(
        val has_next_page: Boolean,
        val last_visible_page: Int
    )
    data class Data(
        val aired: Aired,
        val airing: Boolean,
        val background: String,
        val broadcast: Broadcast,
        val demographics: List<Demographic>,
        val duration: String,
        val episodes: Int,
        val explicit_genres: List<ExplicitGenre>,
        val favorites: Int,
        val genres: List<Genre>,
        val images: Images,
        val licensors: List<Licensor>,
        val mal_id: Int,
        val members: Int,
        val popularity: Int,
        val producers: List<Producer>,
        val rank: Int,
        val rating: String,
        val score: Double,
        val scored_by: Double,
        val season: String,
        val source: String,
        val status: String,
        val studios: List<Studio>,
        val synopsis: String,
        val themes: List<Theme>,
        val title: String,
        val title_english: String,
        val title_japanese: String,
        val title_synonyms: List<String>,
        val trailer: Trailer,
        val type: String,
        val url: String,
        val year: Int
    ) {
        data class Aired(
            val from: String,
            val prop: Prop,
            val to: String
        ) {
            data class Prop(
                val from: From,
                val string: String,
                val to: To
            ) {
                data class From(
                    val day: Int,
                    val month: Int,
                    val year: Int
                )
                data class To(
                    val day: Int,
                    val month: Int,
                    val year: Int
                )
            }
        }
        data class Broadcast(
            val day: String,
            val string: String,
            val time: String,
            val timezone: String
        )
        data class Demographic(
            val mal_id: Int,
            val name: String,
            val type: String,
            val url: String
        )
        data class ExplicitGenre(
            val mal_id: Int,
            val name: String,
            val type: String,
            val url: String
        )
        data class Genre(
            val mal_id: Int,
            val name: String,
            val type: String,
            val url: String
        )
        data class Images(
            val jpg: Jpg,
            val webp: Webp
        ) {
            data class Jpg(
                val image_url: String,
                val large_image_url: String,
                val small_image_url: String
            )
            data class Webp(
                val image_url: String,
                val large_image_url: String,
                val small_image_url: String
            )
        }
        data class Licensor(
            val mal_id: Int,
            val name: String,
            val type: String,
            val url: String
        )
        data class Producer(
            val mal_id: Int,
            val name: String,
            val type: String,
            val url: String
        )
        data class Studio(
            val mal_id: Int,
            val name: String,
            val type: String,
            val url: String
        )
        data class Theme(
            val mal_id: Int,
            val name: String,
            val type: String,
            val url: String
        )
        data class Trailer(
            val embed_url: String,
            val url: String,
            val youtube_id: String
        )
    }
}