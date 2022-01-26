package com.lexwilliam.data.mapper

import com.lexwilliam.data.model.local.MyAnimeRepo
import com.lexwilliam.data.model.local.WatchStatusRepo
import com.lexwilliam.domain.model.local.MyAnime
import com.lexwilliam.domain.model.local.WatchStatus
import javax.inject.Inject

interface MyAnimeMapper {
    fun toRepo(watchStatus: WatchStatus): WatchStatusRepo
    fun toRepo(myAnime: MyAnime): MyAnimeRepo
    fun toDomain(watchStatus: WatchStatusRepo): WatchStatus
    fun toDomain(myAnime: MyAnimeRepo): MyAnime
}

class MyAnimeMapperImpl @Inject constructor(): MyAnimeMapper {

    override fun toRepo(watchStatus: WatchStatus): WatchStatusRepo {
        return when(watchStatus) {
            WatchStatus.Completed -> WatchStatusRepo.Completed
            WatchStatus.Dropped -> WatchStatusRepo.Dropped
            WatchStatus.OnHold -> WatchStatusRepo.OnHold
            WatchStatus.PlanToWatch -> WatchStatusRepo.PlanToWatch
            WatchStatus.Watching -> WatchStatusRepo.Watching
        }
    }

    override fun toRepo(myAnime: MyAnime): MyAnimeRepo =
        MyAnimeRepo(myAnime.mal_id, myAnime.image_url, myAnime.title, myAnime.myScore, toRepo(myAnime.watchStatus!!), myAnime.timeAdded)

    override fun toDomain(watchStatus: WatchStatusRepo): WatchStatus {
        return when(watchStatus) {
            WatchStatusRepo.Completed -> WatchStatus.Completed
            WatchStatusRepo.Dropped -> WatchStatus.Dropped
            WatchStatusRepo.OnHold -> WatchStatus.OnHold
            WatchStatusRepo.PlanToWatch -> WatchStatus.PlanToWatch
            WatchStatusRepo.Watching -> WatchStatus.Watching
        }
    }

    override fun toDomain(myAnime: MyAnimeRepo): MyAnime =
        MyAnime(myAnime.mal_id, myAnime.image_url, myAnime.title, myAnime.myScore, toDomain(myAnime.watchStatus!!), myAnime.timeAdded)

}