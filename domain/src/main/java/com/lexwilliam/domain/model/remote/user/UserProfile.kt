package com.lexwilliam.domain.model.remote.user

data class UserProfile(
    val `data`: Data
) {
    data class Data(
        val about: String,
        val birthday: String,
        val `external`: List<External>,
        val favorites: Favorites,
        val gender: String,
        val images: Images,
        val joined: String,
        val last_online: String,
        val location: String,
        val mal_id: Int,
        val statistics: Statistics,
        val updates: Updates,
        val url: String,
        val username: String
    ) {
        data class Images(
            val jpg: Jpg
        ) {
            data class Jpg(
                val image_url: String
            )
        }
        data class Favorites(
            val anime: List<Anime>,
            val characters: List<Character>,
            val people: List<People>
        ) {
            data class Anime(
                val images: Images,
                val mal_id: Int,
                val start_year: Int,
                val title: String,
                val type: String,
                val url: String
            )
            data class Character(
                val images: Images,
                val mal_id: Int,
                val name: String,
                val url: String
            )
            data class People(
                val images: Images,
                val mal_id: Int,
                val name: String,
                val url: String
            )
        }
        data class External(
            val name: String,
            val url: String
        )
        data class Statistics(
            val anime: Anime
        ) {
            data class Anime(
                val completed: Int,
                val days_watched: Double,
                val dropped: Int,
                val episodes_watched: Int,
                val mean_score: Double,
                val on_hold: Int,
                val plan_to_watch: Int,
                val rewatched: Int,
                val total_entries: Int,
                val watching: Int
            )
        }
        data class Updates(
            val anime: List<Anime>
        ) {
            data class Anime(
                val date: String,
                val entry: Entry,
                val episodes_seen: Int,
                val episodes_total: Int,
                val score: Int,
                val status: String
            ) {
                data class Entry(
                    val images: Images,
                    val mal_id: Int,
                    val title: String,
                    val url: String
                )
            }
        }
    }
}