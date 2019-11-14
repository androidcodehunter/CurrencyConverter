package com.sharif.currencyconverter.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.sharif.currencyconverter.data.model.Rate
import com.sharif.currencyconverter.data.source.RatesRepositoryImpl
import com.sharif.currencyconverter.data.source.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.concurrent.TimeUnit

class RatesConverterViewModel(private val ratesRepository: RatesRepositoryImpl): ViewModel() {

    fun getRates(symbol: String) = liveData{
        emit(Result.Loading)
        repeat(Integer.MAX_VALUE){
            emit(ratesRepository.getRates(symbol))
            delay(ONE_SECOND_IN_MILLIS)
        }
    }

    @Suppress("UNCHECKED_CAST")
    class RatesViewModelFactory(private val ratesRepository: RatesRepositoryImpl): ViewModelProvider.NewInstanceFactory(){
        override fun <T : ViewModel?> create(modelClass: Class<T>): T = (RatesConverterViewModel(ratesRepository) as T)
    }

    companion object{
        const val ONE_SECOND_IN_MILLIS = 1000L
    }

}