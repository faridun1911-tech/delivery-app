package com.delivery.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DeliveryApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize Firebase, Analytics, Crashlytics etc.
    }
}