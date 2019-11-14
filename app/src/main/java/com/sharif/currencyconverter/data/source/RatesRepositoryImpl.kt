package com.sharif.currencyconverter.data.source

import com.sharif.currencyconverter.data.model.RateList
import com.sharif.currencyconverter.data.source.local.RatesLocalDataSource
import com.sharif.currencyconverter.data.source.remote.RatesRemoteDataSource

class RatesRepositoryImpl(private val ratesRemoteDataSource: RatesRemoteDataSource,
                          private val ratesLocalDataSource: RatesLocalDataSource) {

     suspend fun getRates(base: String): Result<RateList?> {
        return ratesRemoteDataSource.getRates(base)
    }

}