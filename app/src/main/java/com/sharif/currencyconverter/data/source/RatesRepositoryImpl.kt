package com.sharif.currencyconverter.data.source

import com.sharif.currencyconverter.data.model.RateList
import com.sharif.currencyconverter.data.source.local.RatesLocalDataSource
import com.sharif.currencyconverter.data.source.remote.RatesRemoteDataSource

class RatesRepositoryImpl(private val ratesLocalDataSource: RatesDataSource,
                          private val ratesRemoteDataSource: RatesDataSource): RatesRepository {

     override suspend fun getRates(base: String): Result<RateList?> {
        return ratesRemoteDataSource.getRates(base)
    }

}