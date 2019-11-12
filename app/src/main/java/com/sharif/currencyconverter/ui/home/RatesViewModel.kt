package com.sharif.currencyconverter.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sharif.currencyconverter.data.source.RatesRepository
import kotlinx.coroutines.Dispatchers

class RatesViewModel(private val ratesRepository: RatesRepository) {

    fun getRates(){
        viewModelScope.launch(Dispatchers.IO){
            ratesRepository.getRates("")
        }

    }

    @Suppress("UNCHECKED_CAST")
    class TasksViewModelFactory(private val ratesRepository: RatesRepository): ViewModelProvider.NewInstanceFactory(){
        override fun <T : ViewModel?> create(modelClass: Class<T>): T = (RatesViewModel(ratesRepository) as T)
    }

}