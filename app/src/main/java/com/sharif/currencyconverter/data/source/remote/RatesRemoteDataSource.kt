package com.sharif.currencyconverter.data.source.remote

import com.sharif.currencyconverter.data.model.RateList
import com.sharif.currencyconverter.data.network.ConverterApiService
import com.sharif.currencyconverter.data.source.RatesDataSource
import com.sharif.currencyconverter.data.source.Result
import java.lang.Exception

class RatesRemoteDataSource(private val converterApiService: ConverterApiService) {

     suspend fun getRates(base: String): Result<RateList?> {
        val response = converterApiService.getTopTracks(base)
         return if (response.isSuccessful){
             Result.Success(response.body())
         }else{
             Result.Error(Exception("Something went wrong "))
         }
        }
    }