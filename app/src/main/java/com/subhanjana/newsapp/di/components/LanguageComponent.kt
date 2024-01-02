package com.subhanjana.newsapp.di.components

import com.subhanjana.newsapp.di.ActivityScope
import com.subhanjana.newsapp.di.modules.LanguageModule
import com.subhanjana.newsapp.ui.activities.LanguageActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [LanguageModule::class])
interface LanguageComponent {
    fun inject(activity : LanguageActivity)
}