package com.subhanjana.newsapp.di.modules

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.subhanjana.newsapp.data.repository.TopHeadlineRepository
import com.subhanjana.newsapp.di.ActivityContext
import com.subhanjana.newsapp.ui.adapters.NewsListAdapter
import com.subhanjana.newsapp.ui.base.ViewModelProviderFactory
import com.subhanjana.newsapp.ui.viewModels.NewsListViewModel
import dagger.Module
import dagger.Provides

@Module
class NewsListModule(private val activity : AppCompatActivity) {

    @ActivityContext
    @Provides
    fun getContext() : Context {
        return activity
    }

    @Provides
    fun providesNewsListViewModel(topHeadlineRepository: TopHeadlineRepository) : NewsListViewModel {
        return ViewModelProvider(activity,ViewModelProviderFactory(NewsListViewModel::class){
            NewsListViewModel(topHeadlineRepository)
        })[NewsListViewModel::class.java]
    }

    @Provides
    fun providesNewsListAdapter() = NewsListAdapter(ArrayList())
}