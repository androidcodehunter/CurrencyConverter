package com.sharif.currencyconverter.data.source

import com.sharif.currencyconverter.data.db.entity.RateList

interface RatesDataSource {
    suspend fun getRates(base : String) : Result<RateList?>
    suspend fun insertOrUpdateRates(rateList: RateList)
}