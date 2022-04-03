package com.lexwilliam.risuto.model

data class AnimeVideosPresentation(
    val `data`: Data
) {
    data class Data(
        val episodes: List<Episode>,
        val promos: List<Promo>
    ) {
        data class Episode(
            val episode: String,
            val images: Images,
            val mal_id: Int,
            val title: String,
            val url: String
        ) {
            data class Images(
                val jpg: Jpg
            ) {
                data class Jpg(
                    val image_url: String
                )
            }
        }
        data class Promo(
            val title: String,
            val trailer: Trailer
        ) {
            data class Trailer(
                val embed_url: String,
                val images: ImagesX,
                val url: String,
                val youtube_id: String
            ) {
                data class ImagesX(
                    val default_image_url: String,
                    val large_image_url: String,
                    val maximum_image_url: String,
                    val medium_image_url: String,
                    val small_image_url: String
                )
            }
        }
    }
}