package com.subhanjana.newsapp.di.modules

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.subhanjana.newsapp.data.repository.TopHeadlineRepository
import com.subhanjana.newsapp.di.ActivityContext
import com.subhanjana.newsapp.ui.adapters.LanguageAdapter
import com.subhanjana.newsapp.ui.base.ViewModelProviderFactory
import com.subhanjana.newsapp.ui.viewModels.LanguageViewModel
import dagger.Module
import dagger.Provides

@Module
class LanguageModule(private val activity : AppCompatActivity) {

    @ActivityContext
    @Provides
    fun providesContext() : Context {
        return activity
    }

    @Provides
    fun providesLanguageViewModel(topHeadlineRepository: TopHeadlineRepository) : LanguageViewModel {
        return ViewModelProvider(activity,ViewModelProviderFactory(LanguageViewModel::class){
            LanguageViewModel(topHeadlineRepository)
        })[LanguageViewModel::class.java]
    }

    @Provides
    fun providesLanguageAdapter() = LanguageAdapter(ArrayList())
}