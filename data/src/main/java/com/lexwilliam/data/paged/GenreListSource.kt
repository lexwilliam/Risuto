package com.lexwilliam.data.paged

//class GenreListSource(
//    private val animeRepository: AnimeRepositoryImpl
//): PagingSource<Int, AnimeListPresentation>() {
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AnimeListPresentation> {
//        return try {
//            val nextPage = params.key ?: 1
//            val searchResponse = animeRepository.genreAnime(QuerySearch(genre = animeRepository.currentGenre, order_by = "members"), nextPage)
//
//            var data: List<AnimeListPresentation> = emptyList()
//            searchResponse.collect {
//                data = it.results.map { it.toDomain().toPresentation() }
//            }
//
//            LoadResult.Page(
//                data = data,
//                prevKey = if (nextPage == 1) null else nextPage - 1,
//                nextKey = if (data.isEmpty()) null else nextPage + 1
//            )
//        } catch (e: Exception) {
//            LoadResult.Error(e)
//        }
//    }
//
//    override fun getRefreshKey(state: PagingState<Int, AnimeListPresentation>): Int? {
//        TODO("Not yet implemented")
//    }
//}