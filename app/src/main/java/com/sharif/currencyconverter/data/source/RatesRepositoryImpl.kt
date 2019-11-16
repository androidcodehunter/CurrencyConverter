package com.sharif.currencyconverter.data.source

import com.sharif.currencyconverter.data.model.RateList
import timber.log.Timber
import java.lang.Exception

class RatesRepositoryImpl(private val ratesLocalDataSource: RatesDataSource,
                          private val ratesRemoteDataSource: RatesDataSource): RatesRepository {

     override suspend fun getRates(base: String, forceUpdate: Boolean): Result<RateList?> {
         try {
             updateRatesFromRemoteDataSource(base)
         }catch (ex: Exception){
             return Result.Error(ex)
         }

        return ratesLocalDataSource.getRates(base)
    }

    private suspend fun updateRatesFromRemoteDataSource(base: String) {
        val response = ratesRemoteDataSource.getRates(base)
        if (response is Result.Success){
            response.data?.let {
                ratesLocalDataSource.insertOrUpdateRates(it)
            }
        }else if (response is Result.Error){
            throw response.exception
        }
    }

}