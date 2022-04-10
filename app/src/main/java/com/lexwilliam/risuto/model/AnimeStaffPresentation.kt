package com.lexwilliam.risuto.model

data class AnimeStaffPresentation(
    val `data`: List<Data>
) {
    data class Data(
        val person: Person,
        val positions: List<String>
    ) {
        data class Person(
            val images: Images,
            val mal_id: Int,
            val name: String,
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

    }
}