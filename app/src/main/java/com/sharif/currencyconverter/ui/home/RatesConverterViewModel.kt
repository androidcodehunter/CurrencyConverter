package com.sharif.currencyconverter.ui.home

import androidx.lifecycle.*
import com.sharif.currencyconverter.data.model.Rate
import com.sharif.currencyconverter.data.model.RateConfig
import com.sharif.currencyconverter.data.entity.RateList
import com.sharif.currencyconverter.data.source.RatesRepository
import com.sharif.currencyconverter.data.source.Result
import kotlinx.coroutines.delay

class RatesConverterViewModel(private val ratesRepository: RatesRepository): ViewModel() {

    private var rates: LiveData<Result<RateList?>>
    private val rateConfig = MutableLiveData<RateConfig>()
    private val updateAmount = MutableLiveData<Double>()
    private var currentBaseSymbol = ""
    private var showLoading = true

    init {
        rates = Transformations.switchMap<RateConfig, Result<RateList?>>(rateConfig){ rateConfig ->
            liveData {
                if (showLoading){
                    emit(Result.Loading)
                    showLoading = false
                }

                //Try to show the saved data
                emit(ratesRepository.getSavedRates(rateConfig.rate.symbol))


                repeat(rateConfig.times){
                    emit(ratesRepository.getRates(rateConfig.rate.symbol, rateConfig.forceUpdate))
                    delay(rateConfig.delayTime)
                }

                currentBaseSymbol = rateConfig.rate.symbol
            }
        }
    }

    /**
     * Provide all rates in repeat times based on configuration.
     */
    fun getRates(): LiveData<Result<RateList?>> {
        return rates
    }

    fun getAmount(): LiveData<Double> {
        return updateAmount
    }

    private fun setAmount(amount: Double){
        updateAmount.postValue(amount)
    }


    /** Set rate configuration or update amount
     *
     *@param times Executes the given function [action] specified number of [times]
     * @param delayTime delay the operation specific time period
     */
    fun setRates(symbol: String = "EUR",
                 amount: Double = 1.0,
                 times: Int = Integer.MAX_VALUE,
                 delayTime: Long = ONE_SECOND_IN_MILLIS,
                 forceUpdate: Boolean = true){
        if (currentBaseSymbol == symbol){
            setAmount(amount)
        }else{
            rateConfig.value = RateConfig(Rate(symbol, amount), times, delayTime, forceUpdate)
        }
        currentBaseSymbol = symbol
    }

    @Suppress("UNCHECKED_CAST")
    class RatesViewModelFactory(private val ratesRepository: RatesRepository): ViewModelProvider.NewInstanceFactory(){
        override fun <T : ViewModel?> create(modelClass: Class<T>): T = (RatesConverterViewModel(ratesRepository) as T)
    }

    companion object{
        const val ONE_SECOND_IN_MILLIS = 1000L
    }

}