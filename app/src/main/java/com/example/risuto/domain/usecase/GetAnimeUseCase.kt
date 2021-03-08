package com.example.risuto.domain.usecase

import com.chun2maru.risutomvvm.data.repository.ListRepository
import com.example.risuto.data.repository.ItemRepository
import javax.inject.Inject

class GetAnimeUseCase
@Inject constructor(
    private val itemRepository: ItemRepository
){
    suspend operator fun invoke(id: Int) = itemRepository.getAnime(id)
}