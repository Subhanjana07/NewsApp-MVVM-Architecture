package com.subhanjana.newsapp.data.api

import com.subhanjana.newsapp.data.model.SourceResponse
import com.subhanjana.newsapp.data.model.TopHeadlineResponse
import retrofit2.http.Headers
import com.subhanjana.newsapp.utils.AppConstants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface NetworkService {
    @Headers("X-Api-Key: $API_KEY")
    @GET("top-headlines")
    suspend fun getTopHeadlines(@Query("country") country: String): TopHeadlineResponse

    @GET("top-headlines/sources")
    suspend fun getNewsSource() : SourceResponse

    @GET("everything")
    suspend fun getSearchResult(@Query("q") searchKey : String) : TopHeadlineResponse
}