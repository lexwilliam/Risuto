package com.example.risuto.domain.usecase.remote

import com.example.risuto.data.remote.repository.ItemRepository
import javax.inject.Inject

class GetSeasonArchiveUseCase
@Inject constructor(
    private val itemRepository: ItemRepository
){
    suspend operator fun invoke() = itemRepository.getSeasonArchive()
}