package com.sharif.currencyconverter.di

import com.sharif.currencyconverter.data.source.RatesRepositoryImpl
import com.sharif.currencyconverter.ui.home.RatesConverterViewModel
import org.koin.dsl.module

val converterViewModels = module{
    factory { RatesConverterViewModel(get() as RatesRepositoryImpl) }
}