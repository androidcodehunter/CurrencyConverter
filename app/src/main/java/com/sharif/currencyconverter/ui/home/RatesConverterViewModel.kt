package com.sharif.currencyconverter.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.sharif.currencyconverter.data.source.RatesRepository
import com.sharif.currencyconverter.data.source.Result
import kotlinx.coroutines.delay

class RatesConverterViewModel(private val ratesRepository: RatesRepository): ViewModel() {

    /**
     * Provide all rates in repeat times based on condition.
     *
     * @param times Executes the given function [action] specified number of [times]
     * @param delayTime delay the operation specific time period
     */
    fun getRatesRepeatUntil(symbol: String, times: Int = Integer.MAX_VALUE, delayTime: Long = ONE_SECOND_IN_MILLIS) = liveData{
        emit(Result.Loading)
        repeat(times){
            emit(ratesRepository.getRates(symbol))
            delay(delayTime)
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