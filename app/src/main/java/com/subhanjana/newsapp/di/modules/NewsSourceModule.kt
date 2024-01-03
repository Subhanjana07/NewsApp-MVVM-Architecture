package com.subhanjana.newsapp.di.modules

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.subhanjana.newsapp.data.repository.TopHeadlineRepository
import com.subhanjana.newsapp.di.ActivityContext
import com.subhanjana.newsapp.ui.adapters.NewsSourceAdapter
import com.subhanjana.newsapp.ui.base.ViewModelProviderFactory
import com.subhanjana.newsapp.ui.viewModels.LanguageViewModel
import com.subhanjana.newsapp.ui.viewModels.NewsSourceViewModel
import dagger.Module
import dagger.Provides

@Module
class NewsSourceModule(private val activity: AppCompatActivity) {

    @ActivityContext
    @Provides
    fun getContext() : Context {
        return activity
    }

    @Provides
    fun providesNewsSourceViewModel(topHeadlineRepository: TopHeadlineRepository) : NewsSourceViewModel {
        return ViewModelProvider(activity,ViewModelProviderFactory(NewsSourceViewModel::class){
            NewsSourceViewModel(topHeadlineRepository)
        })[NewsSourceViewModel::class.java]
    }

    @Provides
    fun providesNewsSourceAdapter() = NewsSourceAdapter(ArrayList())
}