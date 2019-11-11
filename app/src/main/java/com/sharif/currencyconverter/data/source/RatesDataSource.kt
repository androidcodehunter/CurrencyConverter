package com.sharif.currencyconverter.data.source

import com.sharif.currencyconverter.data.model.RateList

interface RatesDataSource {
    suspend fun getRates(base : String) : Result<RateList>
}