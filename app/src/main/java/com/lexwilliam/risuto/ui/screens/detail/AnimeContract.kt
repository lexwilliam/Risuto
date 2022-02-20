package com.lexwilliam.risuto.ui.screens.detail

import com.lexwilliam.risuto.base.ViewEvent
import com.lexwilliam.risuto.base.ViewSideEffect
import com.lexwilliam.risuto.base.ViewState
import com.lexwilliam.risuto.model.detail.*

class AnimeContract {
    sealed class Event : ViewEvent {
        data class InsertAnimeHistory(val anime: AnimeDetailPresentation): Event()
    }

    data class State(
        val animeDetail: AnimeDetailPresentation,
        val characterStaff: CharacterStaffPresentation,
        val episodes: EpisodesPresentation,
        val forum: ForumPresentation,
        val moreInfo: MoreInfoPresentation,
        val pictures: PicturesPresentation,
        val recommendations: RecommendationsPresentation,
        val reviews: ReviewsPresentation,
        val stats: StatsPresentation,
        val videos: VideosPresentation,
        val isLoading: Boolean = false,
        val isError: Boolean = false
    ) : ViewState

    sealed class Effect : ViewSideEffect {}
}