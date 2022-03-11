package com.lexwilliam.risuto.model

data class AnimeCharactersPresentation(
    val `data`: List<Data>
) {
    data class Data(
        val character: Character,
        val role: String,
        val voice_actors: List<VoiceActor>
    ) {
        data class Character(
            val images: Images,
            val mal_id: Int,
            val name: String,
            val url: String
        ) {
            data class Images(
                val jpg: Jpg,
                val webp: Webp
            ) {
                data class Jpg(
                    val image_url: String,
                    val small_image_url: String
                )

                data class Webp(
                    val image_url: String,
                    val small_image_url: String
                )
            }
        }
        data class VoiceActor(
            val language: String,
            val person: Person
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
}