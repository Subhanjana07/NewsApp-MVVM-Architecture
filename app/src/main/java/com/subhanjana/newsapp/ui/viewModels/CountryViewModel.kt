package com.subhanjana.newsapp.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.subhanjana.newsapp.data.model.Country
import com.subhanjana.newsapp.data.repository.TopHeadlineRepository
import com.subhanjana.newsapp.ui.base.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class CountryViewModel(private val topHeadlineRepository: TopHeadlineRepository) : ViewModel() {
    private val _countryUiState = MutableStateFlow<UiState<List<Country>>>(UiState.Loading)
    val countryUiState : StateFlow<UiState<List<Country>>> = _countryUiState
    init{
        fetchCountries()
    }
    fun fetchCountries() {
        viewModelScope.launch {
            topHeadlineRepository.getCountries()
                .catch { e ->
                    _countryUiState.value = UiState.Error(e.toString())
                }
                .collect {
                    _countryUiState.value = UiState.Success(it)
                }
        }
    }
}