package com.chun2maru.risutomvvm.domain.usecase

import com.chun2maru.risutomvvm.data.repository.SearchRepository
import com.chun2maru.risutomvvm.domain.model.SearchAnime
import com.chun2maru.risutomvvm.domain.repository.ISearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

typealias SearchAnimeBaseUseCase = BaseUseCase<String, Flow<List<SearchAnime>>>

class SearchAnimeUseCase
@Inject constructor(
        private val searchAnimeRepository: SearchRepository): SearchAnimeBaseUseCase {

    override suspend operator fun invoke(params: String) = searchAnimeRepository.getSearchResult(params)

}