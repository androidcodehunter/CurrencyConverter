package com.sharif.currencyconverter.data.source

import com.sharif.currencyconverter.data.db.entity.RateList

interface RatesRepository {
    suspend fun getRates(base : String, forceUpdate: Boolean) : Result<RateList?>
    suspend fun getSavedRates(base: String): Result<RateList?>
}