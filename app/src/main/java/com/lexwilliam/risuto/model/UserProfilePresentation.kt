package com.lexwilliam.risuto.model

data class UserProfilePresentation(
    val `data`: Data
) {
    data class Data(
        val birthday: String,
        val gender: String,
        val images: Images,
        val joined: String,
        val last_online: String,
        val location: String,
        val mal_id: Int,
        val url: String,
        val username: String,
        val statistics: Statistics
    ) {
        data class Images(
            val jpg: Jpg,
            val webp: Webp
        ) {
            data class Jpg(
                val image_url: String
            )
            data class Webp(
                val image_url: String
            )
        }
        data class Statistics(
            val anime: Anime,
            val manga: Manga
        ) {
            data class Anime(
                val days_watched: Double,
                val mean_score: Double,
                val watching: Int,
                val completed: Int,
                val on_hold: Int,
                val dropped: Int,
                val plan_to_watch: Int,
                val total_entries: Int,
                val rewatched: Int,
                val episodes_watched: Int
            )
            data class Manga(
                val days_read: Double,
                val mean_score: Double,
                val reading: Int,
                val completed: Int,
                val on_hold: Int,
                val dropped: Int,
                val plan_to_read: Int,
                val total_entries: Int,
                val reread: Int,
                val chapters_read: Int,
                val volumes_read: Int
            )
        }
    }
}