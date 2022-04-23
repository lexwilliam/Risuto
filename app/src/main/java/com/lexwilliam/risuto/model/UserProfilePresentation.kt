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
        val username: String
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
    }
}