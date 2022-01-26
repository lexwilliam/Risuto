package com.lexwilliam.data_local.mapper

import com.lexwilliam.data.model.local.MyAnimeRepo
import com.lexwilliam.data.model.local.WatchStatusRepo
import com.lexwilliam.data_local.model.MyAnimeEntity
import com.lexwilliam.data_local.model.WatchStatusEntity
import javax.inject.Inject

interface MyAnimeMapper {
    fun toRepo(watchStatus: WatchStatusEntity): WatchStatusRepo
    fun toRepo(myAnime: MyAnimeEntity): MyAnimeRepo
    fun toEntity(watchStatus: WatchStatusRepo): WatchStatusEntity
    fun toEntity(myAnime: MyAnimeRepo): MyAnimeEntity
}

class MyAnimeMapperImpl @Inject constructor(): MyAnimeMapper {
    override fun toRepo(watchStatus: WatchStatusEntity): WatchStatusRepo {
        return when(watchStatus) {
            WatchStatusEntity.Completed -> WatchStatusRepo.Completed
            WatchStatusEntity.Dropped -> WatchStatusRepo.Dropped
            WatchStatusEntity.OnHold -> WatchStatusRepo.OnHold
            WatchStatusEntity.PlanToWatch -> WatchStatusRepo.PlanToWatch
            WatchStatusEntity.Watching -> WatchStatusRepo.Watching
        }
    }

    override fun toRepo(myAnime: MyAnimeEntity): MyAnimeRepo =
        MyAnimeRepo(myAnime.mal_id, myAnime.image_url, myAnime.title, myAnime.myScore, toRepo(myAnime.watchStatus!!), myAnime.timeAdded)

    override fun toEntity(watchStatus: WatchStatusRepo): WatchStatusEntity {
        return when(watchStatus) {
            WatchStatusRepo.Completed -> WatchStatusEntity.Completed
            WatchStatusRepo.Dropped -> WatchStatusEntity.Dropped
            WatchStatusRepo.OnHold -> WatchStatusEntity.OnHold
            WatchStatusRepo.PlanToWatch -> WatchStatusEntity.PlanToWatch
            WatchStatusRepo.Watching -> WatchStatusEntity.Watching
        }
    }

    override fun toEntity(myAnime: MyAnimeRepo): MyAnimeEntity =
        MyAnimeEntity(myAnime.mal_id, myAnime.image_url, myAnime.title, myAnime.myScore, toEntity(myAnime.watchStatus!!), myAnime.timeAdded)

}