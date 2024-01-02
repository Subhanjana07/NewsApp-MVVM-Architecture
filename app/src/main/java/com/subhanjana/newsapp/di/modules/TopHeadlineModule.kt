package com.subhanjana.newsapp.di.modules

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.subhanjana.newsapp.data.repository.TopHeadlineRepository
import com.subhanjana.newsapp.di.ActivityContext
import com.subhanjana.newsapp.ui.adapters.TopHeadlineAdapter
import com.subhanjana.newsapp.ui.base.ViewModelProviderFactory
import com.subhanjana.newsapp.ui.viewModels.TopHeadlineViewModel
import dagger.Module
import dagger.Provides

@Module
class TopHeadlineModule(private val activity : AppCompatActivity) {
    @ActivityContext
    @Provides
    fun provideContext() : Context {
        return activity
    }
    @Provides
    fun provideNewsListViewModel(topHeadlineRepository: TopHeadlineRepository) : TopHeadlineViewModel {
        return ViewModelProvider(activity,ViewModelProviderFactory(TopHeadlineViewModel::class) {
                TopHeadlineViewModel(topHeadlineRepository)
            })[TopHeadlineViewModel::class.java]
    }
    @Provides
    fun providesTopHeadlineAdapter() = TopHeadlineAdapter(ArrayList())
}