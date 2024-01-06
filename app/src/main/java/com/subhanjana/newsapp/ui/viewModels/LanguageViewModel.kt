package com.subhanjana.newsapp.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.subhanjana.newsapp.data.model.Language
import com.subhanjana.newsapp.data.repository.TopHeadlineRepository
import com.subhanjana.newsapp.ui.base.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class LanguageViewModel(private val topHeadlineRepository: TopHeadlineRepository) : ViewModel() {
    private val _languageUiState = MutableStateFlow<UiState<List<Language>>>(UiState.Loading)
    val languageUiState : StateFlow<UiState<List<Language>>> = _languageUiState
    init{
        fetchLanguage()
    }
    fun fetchLanguage() {
        viewModelScope.launch {
            topHeadlineRepository.getLanguages()
                .catch { e ->
                    _languageUiState.value = UiState.Error(e.toString())
                }
                .collect {
                    _languageUiState.value = UiState.Success(it)
                }
        }
    }
}