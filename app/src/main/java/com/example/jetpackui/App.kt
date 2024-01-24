package com.example.jetpackui

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * Created by Asieuzzaman Wasir on 15,January,2024
 */
@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())

    }
}