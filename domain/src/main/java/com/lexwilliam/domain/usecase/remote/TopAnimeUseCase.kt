package com.example.risuto.domain.usecase.remote

import com.chun2maru.risutomvvm.data.repository.ListRepository
import com.lexwilliam.domain.model.TopAnime
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TopAnimeUseCase
@Inject constructor(
    private val listRepository: ListRepository) {

    suspend operator fun invoke(
        page: Int, subType: String
    ): Flow<List<TopAnime>> = listRepository.topAnime(page, subType)

}