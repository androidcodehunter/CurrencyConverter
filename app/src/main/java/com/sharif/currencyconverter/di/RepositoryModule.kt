package com.sharif.currencyconverter.di

import com.sharif.currencyconverter.data.source.RatesRepositoryImpl
import com.sharif.currencyconverter.data.source.local.RatesLocalDataSource
import com.sharif.currencyconverter.data.source.remote.RatesRemoteDataSource
import org.koin.dsl.module

//Injecting @ConverterApiService into Repository constructor.
val converterRepositoryModule = module {
    factory { RatesLocalDataSource() }
    factory { RatesRemoteDataSource(get()) }
    factory { RatesRepositoryImpl(get(), get()) }
}
