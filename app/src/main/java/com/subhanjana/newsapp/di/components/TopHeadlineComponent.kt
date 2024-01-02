package com.subhanjana.newsapp.di.components

import com.subhanjana.newsapp.di.ActivityScope
import com.subhanjana.newsapp.di.modules.TopHeadlineModule
import com.subhanjana.newsapp.ui.activities.TopHeadlineActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [TopHeadlineModule::class])
interface TopHeadlineComponent {
    fun inject(activity: TopHeadlineActivity)
}