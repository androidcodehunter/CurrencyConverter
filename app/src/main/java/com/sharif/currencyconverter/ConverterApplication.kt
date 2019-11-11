package com.sharif.currencyconverter

import android.app.Application
import timber.log.Timber

class ConverterApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
    }
}