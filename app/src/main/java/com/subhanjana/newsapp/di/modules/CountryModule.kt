package com.subhanjana.newsapp.di.modules

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.subhanjana.newsapp.data.repository.TopHeadlineRepository
import com.subhanjana.newsapp.di.ActivityContext
import com.subhanjana.newsapp.ui.activities.TopHeadlineActivity
import com.subhanjana.newsapp.ui.adapters.CountryAdapter
import com.subhanjana.newsapp.ui.base.ViewModelProviderFactory
import com.subhanjana.newsapp.ui.viewModels.CountryViewModel
import dagger.Module
import dagger.Provides

@Module
class CountryModule(private val activity: AppCompatActivity) {

    @ActivityContext
    @Provides
    fun getContext() : Context {
        return activity
    }

    @Provides
    fun providesCountryViewModel(topHeadlineRepository: TopHeadlineRepository) : CountryViewModel {
        return ViewModelProvider(activity,ViewModelProviderFactory(CountryViewModel::class){
            CountryViewModel((topHeadlineRepository))
        })[CountryViewModel::class.java]
    }

    @Provides
    fun proviesCountryAdapter() = CountryAdapter(ArrayList())
}