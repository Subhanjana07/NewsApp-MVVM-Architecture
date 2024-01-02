package com.subhanjana.newsapp

import android.app.Application
import com.subhanjana.newsapp.di.components.ApplicationComponent
import com.subhanjana.newsapp.di.components.DaggerApplicationComponent
import com.subhanjana.newsapp.di.modules.ApplicationModule

class NewsApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }

}