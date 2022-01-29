package com.lexwilliam.risutov2.mapper

import com.lexwilliam.domain.model.local.MyAnime
import com.lexwilliam.domain.model.local.WatchStatus
import com.lexwilliam.risutov2.model.AnimePresentation
import com.lexwilliam.risutov2.model.detail.AnimeDetailPresentation
import com.lexwilliam.risutov2.model.local.MyAnimePresentation
import com.lexwilliam.risutov2.model.local.WatchStatusPresentation
import javax.inject.Inject

interface MyAnimeMapper {
    fun toPresentation(watchStatus: WatchStatus): WatchStatusPresentation
    fun toPresentation(myAnime: MyAnime): AnimePresentation
    fun toDomain(watchStatus: WatchStatusPresentation): WatchStatus
    fun toDomain(anime: AnimePresentation): MyAnime
    fun toDomain(myAnime: MyAnimePresentation): MyAnime
}

class MyAnimeMapperImpl @Inject constructor(): MyAnimeMapper {

    override fun toPresentation(watchStatus: WatchStatus): WatchStatusPresentation {
        return when(watchStatus) {
            WatchStatus.Completed -> WatchStatusPresentation.Completed
            WatchStatus.Dropped -> WatchStatusPresentation.Dropped
            WatchStatus.OnHold -> WatchStatusPresentation.OnHold
            WatchStatus.PlanToWatch -> WatchStatusPresentation.PlanToWatch
            WatchStatus.Watching -> WatchStatusPresentation.Watching
            WatchStatus.Default -> WatchStatusPresentation.Default
        }
    }

    override fun toPresentation(myAnime: MyAnime): AnimePresentation =
        AnimePresentation(
            mal_id = myAnime.mal_id,
            image_url = myAnime.image_url,
            title = myAnime.title,
            my_score = myAnime.myScore,
            watch_status = toPresentation(myAnime.watchStatus!!),
            timeAdded = myAnime.timeAdded
        )

    override fun toDomain(watchStatus: WatchStatusPresentation): WatchStatus {
        return when(watchStatus) {
            WatchStatusPresentation.Completed -> WatchStatus.Completed
            WatchStatusPresentation.Dropped -> WatchStatus.Dropped
            WatchStatusPresentation.OnHold -> WatchStatus.OnHold
            WatchStatusPresentation.PlanToWatch -> WatchStatus.PlanToWatch
            WatchStatusPresentation.Watching -> WatchStatus.Watching
            WatchStatusPresentation.Default -> WatchStatus.Default
        }
    }

    override fun toDomain(anime: AnimePresentation): MyAnime =
        MyAnime(
            mal_id = anime.mal_id!!,
            image_url = anime.image_url!!,
            title = anime.title!!,
            myScore = anime.my_score!!,
            watchStatus = toDomain(anime.watch_status!!),
            timeAdded = anime.timeAdded
        )

    override fun toDomain(myAnime: MyAnimePresentation): MyAnime =
        MyAnime(
            mal_id = myAnime.mal_id,
            image_url = myAnime.image_url,
            title = myAnime.title,
            myScore = myAnime.myScore,
            watchStatus = toDomain(myAnime.watchStatus!!),
            timeAdded = myAnime.timeAdded
        )
}