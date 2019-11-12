package com.sharif.currencyconverter.data.source.remote

import com.sharif.currencyconverter.data.model.RateList
import com.sharif.currencyconverter.data.network.ConverterApiService
import com.sharif.currencyconverter.data.source.RatesDataSource
import com.sharif.currencyconverter.data.source.Result

class RatesRemoteDataSource(private val converterApiService: ConverterApiService): RatesDataSource {
    
    override suspend fun getRates(base: String): Result<RateList> {
        return Result.Success(converterApiService.getTopTracks(base))
    }

}