package com.sharif.currencyconverter.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.sharif.currencyconverter.data.source.RatesRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class RatesConverterViewModel(private val ratesRepository: RatesRepositoryImpl): ViewModel() {

    fun getRates(){
        viewModelScope.launch(Dispatchers.IO){
            ratesRepository.getRates("EUR")
            Timber.d("Rates repository called")
        }
    }

    @Suppress("UNCHECKED_CAST")
    class RatesViewModelFactory(private val ratesRepository: RatesRepositoryImpl): ViewModelProvider.NewInstanceFactory(){
        override fun <T : ViewModel?> create(modelClass: Class<T>): T = (RatesConverterViewModel(ratesRepository) as T)
    }

}