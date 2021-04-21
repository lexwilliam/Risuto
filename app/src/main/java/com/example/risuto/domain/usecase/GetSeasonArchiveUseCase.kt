package com.example.risuto.domain.usecase

import com.example.risuto.data.repository.ItemRepository
import javax.inject.Inject

class GetSeasonArchiveUseCase
@Inject constructor(
    private val itemRepository: ItemRepository
){
    suspend operator fun invoke() = itemRepository.getSeasonArchive()
}