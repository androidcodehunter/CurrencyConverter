package com.sharif.currencyconverter.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.sharif.currencyconverter.data.source.RatesRepository
import com.sharif.currencyconverter.data.source.Result
import kotlinx.coroutines.delay

class RatesConverterViewModel(private val ratesRepository: RatesRepository): ViewModel() {

    fun getRates(symbol: String) = liveData{
        emit(Result.Loading)
        repeat(Integer.MAX_VALUE){
            emit(ratesRepository.getRates(symbol))
            delay(ONE_SECOND_IN_MILLIS)
        }
    }

    @Suppress("UNCHECKED_CAST")
    class RatesViewModelFactory(private val ratesRepository: RatesRepository): ViewModelProvider.NewInstanceFactory(){
        override fun <T : ViewModel?> create(modelClass: Class<T>): T = (RatesConverterViewModel(ratesRepository) as T)
    }

    companion object{
        const val ONE_SECOND_IN_MILLIS = 1000L
    }

}