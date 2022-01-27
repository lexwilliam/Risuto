package com.lexwilliam.risutov2.ui.search
//
//import android.util.Log
//import androidx.lifecycle.SavedStateHandle
//import com.example.risuto.data.local.Results
//import com.example.risuto.domain.usecase.local.*
//import com.lexwilliam.domain.usecase.local.*
//import com.lexwilliam.domain.usecase.remote.GetSearchAnime
//import com.lexwilliam.risutov2.base.BaseViewModel
//import com.lexwilliam.risutov2.mapper.toDomain
//import com.lexwilliam.risutov2.model.AnimePresentation
//import com.lexwilliam.risutov2.model.local.SearchHistoryPresentation
//import com.lexwilliam.risutov2.util.Error
//import com.lexwilliam.risutov2.util.ExceptionHandler
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.CoroutineExceptionHandler
//import kotlinx.coroutines.Job
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.asStateFlow
//import javax.inject.Inject
//
//@HiltViewModel
//class SearchViewModel
//    @Inject constructor(
//        private val getSearchAnime: GetSearchAnime,
//        private val getAllSearchHistory: GetSearchHistory,
//        private val insertSearchHistory: InsertSearchHistory,
//        private val deleteSearchHistory: DeleteSearchHistory,
//        private val deleteAllSearchHistory: DeleteAllSearchHistory,
//        private val deleteAnimeHistory: DeleteAnimeByTitle,
//        private val deleteAllAnimeHistory: DeleteAllAnimeHistory,
//        private val getAllAnimeHistory: GetAnimeHistory,
//        private val savedStateHandle: SavedStateHandle,
//    ): BaseViewModel() {
//
//    override val coroutineExceptionHandler= CoroutineExceptionHandler { _, exception ->
//        val message = ExceptionHandler.parse(exception)
//        onSearchError(message)
//    }
//
//    private var searchJob: Job? = null
//    private var getJob: Job? = null
//    private var insertJob: Job? = null
//    private var deleteJob: Job? = null
//
//    override fun onCleared() {
//        super.onCleared()
//        searchJob?.cancel()
//    }
//
//    init {
//        loadHistory()
//    }
//
//    private var _state = MutableStateFlow(SearchViewState(error = null, isLoading = false))
//    val state = _state.asStateFlow()
//
//    fun onSearchAnime() {
//        searchJob?.cancel()
//        searchJob = launchCoroutine {
//            onSearchLoading()
//            _state.value = _state.value.copy(query = query.value)
//            searchAnimeUseCase.invoke(query.value).collect { results ->
//                val animes = results.map { anime -> anime.toPresentation() }
//                onSearchComplete(animes)
//            }
//        }
//    }
//
//    fun getQuery(q: QuerySearch) {
//        query.value = query.value.copy(q = q.q)
//        query.value = query.value.copy(limit = q.limit)
//    }
//
//    private fun onSearchComplete(animes: List<AnimePresentation>) {
//        _state.value = _state.value.copy(searchAnimes = animes, error = null, isLoading = false)
//    }
//
//    private fun onSearchLoading() {
//        _state.value = _state.value.copy(searchAnimes = emptyList(), isLoading = true)
//    }
//
//    private fun onSearchError(message: Int){
//        _state.value = _state.value.copy(searchAnimes = emptyList(), error = Error(message), isLoading = false)
//    }
//
//    private fun loadHistory() {
//        getJob = launchCoroutine {
//            getAllSearchHistoryUseCase.invoke().collect { results ->
//                val history = results.map { it.toPresentation() }
//                _state.value = _state.value.copy(searchHistory = history)
//            }
//        }
//        getJob = launchCoroutine {
//            getAllAnimeHistoryUseCase.invoke().collect { results ->
//                val history = results.map { it.toPresentation() }
//                _state.value = _state.value.copy(animeHistory = history)
//            }
//        }
//    }
//
//    fun insertSearchHistory(query: SearchHistoryPresentation) {
//        insertJob = launchCoroutine {
//            insertSearchHistoryUseCase.invoke(query.toDomain()).collect { result ->
//                if(result == Results.SUCCESS) {
//                    Log.d("TAG", "Saving Success")
//                } else {
//                    Log.d("TAG", "Saving Failed")
//                }
//            }
//        }
//    }
//
//    fun deleteAllSearchHistory() {
//        deleteJob = launchCoroutine {
//            deleteAllSearchHistoryUseCase.invoke().collect { result ->
//                if(result == -1) run {
//                    Log.d("TAG", "Delete Failed")
//                } else {
//                    Log.d("TAG", "Delete Success")
//                }
//            }
//        }
//    }
//
//    fun deleteSearchHistory(query: String) {
//        deleteJob = launchCoroutine {
//            deleteSearchHistoryUseCase.invoke(query).collect { result ->
//                if(result == -1) run {
//                    Log.d("TAG", "Delete Failed")
//                } else {
//                    Log.d("TAG", "Delete Success")
//                }
//            }
//        }
//    }
//
//    fun deleteAllAnimeHistory() {
//        deleteJob = launchCoroutine {
//            deleteAllAnimeHistoryUseCase.invoke().collect { result ->
//                if(result == -1) run {
//                    Log.d("TAG", "Delete Failed")
//                } else {
//                    Log.d("TAG", "Delete Success")
//                }
//            }
//        }
//    }
//
//    fun deleteAnimeHistory(title: String) {
//        deleteJob = launchCoroutine {
//            deleteAnimeHistoryUseCase.invoke(title).collect { result ->
//                if(result == -1) run {
//                    Log.d("TAG", "Delete Failed")
//                } else {
//                    Log.d("TAG", "Delete Success")
//                }
//            }
//        }
//    }
//}
//
//data class SearchViewState(
//    val query: QuerySearch,
//    val searchAnimes: List<AnimePresentation> = emptyList(),
//    val animeHistory: List<AnimePresentation> = emptyList(),
//    val searchHistory: List<SearchHistoryPresentation> = emptyList(),
//    val error: Error?,
//    val isLoading: Boolean
//)