package com.subhanjana.newsapp.di.components

import com.subhanjana.newsapp.di.ActivityScope
import com.subhanjana.newsapp.di.modules.SearchModule
import com.subhanjana.newsapp.ui.activities.SearchActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [SearchModule::class])
interface SearchComponent {
    fun inject(activity : SearchActivity)
}