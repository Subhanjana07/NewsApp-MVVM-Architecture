package com.subhanjana.newsapp.di.components

import com.subhanjana.newsapp.di.ActivityScope
import com.subhanjana.newsapp.di.modules.CountryModule
import com.subhanjana.newsapp.ui.activities.CountriesActivity
import com.subhanjana.newsapp.ui.activities.TopHeadlineActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [CountryModule::class])
interface CountryComponent {
    fun inject(activity: CountriesActivity)
}