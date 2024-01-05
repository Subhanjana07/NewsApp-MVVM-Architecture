package com.subhanjana.newsapp.di.components

import com.subhanjana.newsapp.di.ActivityScope
import com.subhanjana.newsapp.di.modules.NewsListModule
import com.subhanjana.newsapp.ui.activities.NewsListActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [NewsListModule::class])
interface NewsListComponent {
    fun inject(activity : NewsListActivity)
}