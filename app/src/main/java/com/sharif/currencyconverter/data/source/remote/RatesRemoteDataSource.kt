package com.sharif.currencyconverter.data.source.remote

import com.sharif.currencyconverter.data.model.RateList
import com.sharif.currencyconverter.data.source.RatesDataSource

object RatesRemoteDataSource: RatesDataSource {
    override suspend fun getRates(base: String): RateList {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}