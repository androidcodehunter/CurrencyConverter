package com.sharif.currencyconverter.di

import com.sharif.currencyconverter.data.db.AppDatabase
import com.sharif.currencyconverter.data.source.RatesRepositoryImpl
import com.sharif.currencyconverter.data.source.local.RatesLocalDataSource
import com.sharif.currencyconverter.data.source.remote.RatesRemoteDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

//Injecting @ConverterApiService into Repository constructor.
val converterRepositoryModule = module {
    factory { RatesLocalDataSource(get()) }
    factory { RatesRemoteDataSource(get()) }
    factory { AppDatabase(androidContext()) }
    single { RatesRepositoryImpl(get() as RatesLocalDataSource, get() as RatesRemoteDataSource) }
}
