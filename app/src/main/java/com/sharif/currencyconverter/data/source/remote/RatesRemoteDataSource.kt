package com.sharif.currencyconverter.data.source.remote

import com.sharif.currencyconverter.data.model.RateList
import com.sharif.currencyconverter.data.network.ConverterApiService
import com.sharif.currencyconverter.data.source.RatesDataSource
import com.sharif.currencyconverter.data.source.Result

class RatesRemoteDataSource(private val converterApiService: ConverterApiService): RatesDataSource {

       override suspend fun getRates(base: String): Result<RateList?> {
           return try {
               val response = converterApiService.getRateList(base)
               if (response.isSuccessful) {
                   Result.Success(response.body())
               } else {
                   Result.Error(Exception("Something went wrong "))
               }
           } catch (e: Exception) {
               Result.Error(e)
           }
       }

    override suspend fun insertOrUpdateRates(rateList: RateList){
    }
}