package com.example.risuto.domain.usecase.remote

import com.chun2maru.risutomvvm.data.repository.ListRepository
import com.lexwilliam.domain.model.SeasonAnime
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SeasonAnimeUseCase
@Inject constructor(
    private val listRepository: ListRepository
) {
    suspend operator fun invoke(
        year: Int, season: String
    ): Flow<List<SeasonAnime>> = listRepository.seasonAnime(year, season)
}