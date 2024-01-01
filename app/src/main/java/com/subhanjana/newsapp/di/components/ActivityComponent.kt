package com.subhanjana.newsapp.di.components

import com.subhanjana.newsapp.di.ActivityScope
import com.subhanjana.newsapp.di.modules.ActivityModule
import com.subhanjana.newsapp.ui.activities.MainActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: MainActivity)

}