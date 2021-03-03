package com.example.risuto.domain.usecase

import com.chun2maru.risutomvvm.data.repository.ItemRepository
import com.example.risuto.domain.model.TopItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTopResultUseCase
@Inject constructor(
    private val itemRepository: ItemRepository) {

    suspend operator fun invoke(
        type: String, page: Int, subType: String
    ): Flow<List<TopItem>> = itemRepository.getTopResult(type, page, subType)

}