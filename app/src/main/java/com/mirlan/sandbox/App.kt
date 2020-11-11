package com.mirlan.sandbox

import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDexApplication
import com.mirlan.sandbox.BuildConfig
import com.mirlan.sandbox.di.appModule
import com.mirlan.sandbox.utils.Settings
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class App : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        setNightMode()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
    }

    private fun setNightMode() {
        val prefs = PreferenceManager.getDefaultSharedPreferences(this)
        val mode = prefs.getInt(Settings.NIGHT_MODE, Settings.MODE_NIGHT_DEFAULT)
        AppCompatDelegate.setDefaultNightMode(mode)
    }
}