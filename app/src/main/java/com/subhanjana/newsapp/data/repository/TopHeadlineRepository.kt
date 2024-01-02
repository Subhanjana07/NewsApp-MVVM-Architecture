package com.subhanjana.newsapp.data.repository

import com.subhanjana.newsapp.data.api.NetworkService
import com.subhanjana.newsapp.data.model.Article
import com.subhanjana.newsapp.data.model.Country
import com.subhanjana.newsapp.utils.Utils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TopHeadlineRepository @Inject constructor(private val networkService: NetworkService) {
    fun getTopHeadlines(country : String) : Flow<List<Article>>{
        return  flow {
            emit(networkService.getTopHeadlines(country))
        }.map {
            it.articles
        }
    }
    fun getCountries(): Flow<List<Country>>{
        return flow {
            emit(Utils.getCountries())
        }
    }
}