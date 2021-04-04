package com.example.risuto.domain.usecase

import com.chun2maru.risutomvvm.data.repository.ListRepository
import com.example.risuto.domain.model.SeasonAnime
import com.example.risuto.domain.model.TopAnime
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