package com.mirlan.sandbox

import androidx.multidex.MultiDexApplication
import com.mirlan.sandbox.BuildConfig
import com.mirlan.sandbox.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class App: MultiDexApplication(){
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
    }
}