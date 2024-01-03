package com.subhanjana.newsapp.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.subhanjana.newsapp.data.model.Source
import com.subhanjana.newsapp.data.repository.TopHeadlineRepository
import com.subhanjana.newsapp.ui.base.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NewsSourceViewModel(private val topHeadlineRepository: TopHeadlineRepository) : ViewModel() {
    private val _sourceUiState = MutableStateFlow<UiState<List<Source>>>(UiState.Loading)
    val sourceUiState : StateFlow<UiState<List<Source>>> = _sourceUiState
    init{
        fetchSource()
    }
    private fun fetchSource(){
        viewModelScope.launch {
            topHeadlineRepository.getNewsSources()
                .catch { e->
                    _sourceUiState.value = UiState.Error(e.toString())
                }
                .collect{
                    _sourceUiState.value = UiState.Success(it)
                }
        }
    }
}