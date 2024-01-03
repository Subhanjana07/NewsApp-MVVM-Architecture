package com.subhanjana.newsapp.di.modules

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.subhanjana.newsapp.data.repository.TopHeadlineRepository
import com.subhanjana.newsapp.di.ActivityContext
import com.subhanjana.newsapp.ui.adapters.SearchAdapter
import com.subhanjana.newsapp.ui.base.ViewModelProviderFactory
import com.subhanjana.newsapp.ui.viewModels.SearchViewModel
import dagger.Module
import dagger.Provides

@Module
class SearchModule(private val activity: AppCompatActivity) {
    @ActivityContext
    @Provides
    fun providesContext() : Context {
        return activity
    }

    @Provides
    fun providesSearchViewModel(topHeadlineRepository: TopHeadlineRepository) : SearchViewModel {
        return ViewModelProvider(activity,ViewModelProviderFactory(SearchViewModel::class){
            SearchViewModel((topHeadlineRepository))
        })[SearchViewModel::class.java]
    }

    @Provides
    fun providesSearchAdapter() = SearchAdapter(ArrayList())
}