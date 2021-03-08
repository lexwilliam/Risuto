package com.example.risuto.domain.usecase

import com.chun2maru.risutomvvm.data.repository.ListRepository
import com.example.risuto.domain.model.TopAnime
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TopAnimeUseCase
@Inject constructor(
    private val listRepository: ListRepository) {

    suspend operator fun invoke(
        type: String, page: Int, subType: String
    ): Flow<List<TopAnime>> = listRepository.topAnime(type, page, subType)

}