package com.example.risuto.domain.usecase.remote.detail

import com.example.risuto.data.remote.repository.ItemRepository
import javax.inject.Inject

class GetEpisodesUseCase
@Inject constructor(
    private val itemRepository: ItemRepository
){
    suspend operator fun invoke(id: Int) = itemRepository.getEpisodes(id)
}