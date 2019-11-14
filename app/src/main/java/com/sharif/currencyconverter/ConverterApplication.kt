package com.sharif.currencyconverter

import android.app.Application
import com.sharif.currencyconverter.di.converterRepositoryModule
import com.sharif.currencyconverter.di.converterViewModels
import com.sharif.currencyconverter.di.netWorkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class ConverterApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoinForDependency()
        Timber.plant(Timber.DebugTree())
    }

    /**
     * Initialize all dependencies here.
     * All dependency related code available in @di package.
     */
    private fun startKoinForDependency() {
        startKoin {
            androidContext(this@ConverterApplication)
            modules(listOf(netWorkModule,
                converterRepositoryModule,
                converterViewModels))
        }
    }

}