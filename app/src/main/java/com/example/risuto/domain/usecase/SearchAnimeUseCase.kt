package com.chun2maru.risutomvvm.domain.usecase

import com.chun2maru.risutomvvm.data.repository.ItemRepository
import javax.inject.Inject

class SearchAnimeUseCase
@Inject constructor(
    private val itemRepository: ItemRepository) {

    suspend operator fun invoke(params: String) = itemRepository.getSearchResult(params)
}