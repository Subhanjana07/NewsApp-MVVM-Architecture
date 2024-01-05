package com.subhanjana.newsapp.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.subhanjana.newsapp.data.model.Article
import com.subhanjana.newsapp.data.repository.TopHeadlineRepository
import com.subhanjana.newsapp.ui.base.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NewsListViewModel(private val topHeadlineRepository: TopHeadlineRepository) : ViewModel() {
    private val _newsListUiState = MutableStateFlow<UiState<List<Article>>>(UiState.Loading)
    val newsListUiState : StateFlow<UiState<List<Article>>> = _newsListUiState

    fun fetchNewsBySource(sources : String) {
        viewModelScope.launch {
            topHeadlineRepository.getNewsBySource(sources)
                .catch { e ->
                    _newsListUiState.value = UiState.Error(e.toString())
                }
                .collect{
                    _newsListUiState.value = UiState.Success(it)
                }
        }
    }

    fun fetchNewsByLanguage(language : String) {
        viewModelScope.launch {
            topHeadlineRepository.getNewsByLanguage(language)
                .catch { e ->
                    _newsListUiState.value = UiState.Error(e.toString())
                }
                .collect{
                    _newsListUiState.value = UiState.Success(it)
                }
        }
    }

    fun fetchNewsByCountry(country : String) {
        viewModelScope.launch {
            topHeadlineRepository.getTopHeadlines(country)
                .catch { e ->
                    _newsListUiState.value = UiState.Error(e.toString())
                }
                .collect{
                    _newsListUiState.value = UiState.Success(it)
                }
        }
    }
}