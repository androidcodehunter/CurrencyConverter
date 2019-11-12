package com.sharif.currencyconverter.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.sharif.currencyconverter.data.source.RatesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RatesViewModel(private val ratesRepository: RatesRepository): ViewModel() {

    fun getRates(){
        viewModelScope.launch(Dispatchers.IO){
            ratesRepository.getRates("EUR")
        }
    }

    @Suppress("UNCHECKED_CAST")
    class RatesViewModelFactory(private val ratesRepository: RatesRepository): ViewModelProvider.NewInstanceFactory(){
        override fun <T : ViewModel?> create(modelClass: Class<T>): T = (RatesViewModel(ratesRepository) as T)
    }

}