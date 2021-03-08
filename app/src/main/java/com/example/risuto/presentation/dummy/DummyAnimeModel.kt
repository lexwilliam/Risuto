package com.example.risuto.presentation.dummy

data class DummyAnimeModel(
    val name: String,
    val score: Float,
    val members: Int
)

fun generateDummyAnime(): List<DummyAnimeModel>{
    val animes = listOf(
        DummyAnimeModel("Wonder Egg Priority", 9.6f, 72132),
        DummyAnimeModel("Attack On Titan", 10.0f, 320991),
        DummyAnimeModel("Sakurasou no Pet Kanojo", 8.7f, 421312),
        DummyAnimeModel("Rental Girlfriend", 8.3f, 21324),
        DummyAnimeModel("Jobless Reincarnation", 9.3f, 50232),
        DummyAnimeModel("Sword Art Online", 7.4f, 1000000)
    )
    return animes
}