package com.subhanjana.newsapp.di.components

import android.content.Context
import com.subhanjana.newsapp.NewsApplication
import com.subhanjana.newsapp.data.api.NetworkService
import com.subhanjana.newsapp.data.repository.TopHeadlineRepository
import com.subhanjana.newsapp.di.ApplicationContext
import com.subhanjana.newsapp.di.modules.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: NewsApplication)

    @ApplicationContext
    fun getContext(): Context

    fun getNetworkService(): NetworkService

    fun getTopHeadlineRepository(): TopHeadlineRepository

}