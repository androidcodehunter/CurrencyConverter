package com.sharif.currencyconverter.data.source.local

import com.sharif.currencyconverter.data.model.RateList
import com.sharif.currencyconverter.data.source.RatesDataSource
import com.sharif.currencyconverter.data.source.Result

class RatesLocalDataSource: RatesDataSource {

    override suspend fun getRates(base: String): Result<RateList> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}