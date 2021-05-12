package com.example.risuto.domain.usecase.remote

import com.example.risuto.data.remote.repository.ItemRepository
import javax.inject.Inject

class GetCharacterStaffUseCase
@Inject constructor(
    private val itemRepository: ItemRepository
){
    suspend operator fun invoke(id: Int) = itemRepository.getCharacterStaff(id)
}