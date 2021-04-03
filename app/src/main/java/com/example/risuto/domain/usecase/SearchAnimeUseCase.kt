package com.chun2maru.risutomvvm.domain.usecase

import com.chun2maru.risutomvvm.data.repository.ListRepository
import com.example.risuto.presentation.model.QuerySearch
import javax.inject.Inject

class SearchAnimeUseCase
@Inject constructor(
    private val listRepository: ListRepository) {

    suspend operator fun invoke(params: QuerySearch) = listRepository.searchAnime(params)
}