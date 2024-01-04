package com.subhanjana.newsapp.ui.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.subhanjana.newsapp.data.model.Article
import com.subhanjana.newsapp.data.repository.TopHeadlineRepository
import com.subhanjana.newsapp.ui.base.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class SearchViewModel(private val topHeadlineRepository: TopHeadlineRepository) : ViewModel() {

    private val _searchUiState = MutableStateFlow<UiState<List<Article>>>(UiState.Loading)
    val searchUiState : StateFlow<UiState<List<Article>>> = _searchUiState
    private val tag : String = SearchViewModel::class.java.name

    fun getSearchResult(queryFlow: StateFlow<String>)  {
        viewModelScope.launch {
            queryFlow.debounce(300)
                .filter { query ->
                    if (query.isEmpty()) {
                        _searchUiState.value = UiState.Success(emptyList())
                        return@filter false
                    }else {
                        return@filter true
                    }
                }
                .distinctUntilChanged()
                .flatMapLatest { query ->
                    Log.d(tag, query)
                    return@flatMapLatest topHeadlineRepository.getSearchResult(query)
                        .catch {
                            Log.e(tag,"Error retrieving the data ")
                        emitAll(flowOf(emptyList()))
                    }
                }.flowOn(Dispatchers.IO)
                .collect {
                    Log.d(tag, it.toString())
                    _searchUiState.value = UiState.Success(it)
                }
        }
    }
}