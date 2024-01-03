package com.subhanjana.newsapp.di.components

import com.subhanjana.newsapp.di.ActivityScope
import com.subhanjana.newsapp.di.modules.NewsSourceModule
import com.subhanjana.newsapp.ui.activities.NewsSourcesActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [NewsSourceModule::class])
interface NewsSourceComponent {
    fun inject(activity: NewsSourcesActivity)
}