package com.lexwilliam.risuto.model

data class UserAnimeListPresentation(
    val `data`: List<Data>
) {
    data class Data(
        val listStatus: ListStatus,
        val node: Node
    ) {
        data class ListStatus(
            val isReWatching: Boolean,
            val numWatchedEpisodes: Int,
            val score: Int,
            val status: WatchStatusPresentation,
            val updatedAt: String
        )
        data class Node(
            val id: Int,
            val numTotalEpisodes: Int,
            val mainPicture: MainPicture,
            val title: String
        ) {
            data class MainPicture(
                val large: String,
                val medium: String
            )
        }
    }
}