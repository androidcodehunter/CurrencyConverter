package com.sharif.currencyconverter.data.source.local

import com.sharif.currencyconverter.data.model.RateList
import com.sharif.currencyconverter.data.source.RatesDataSource
import com.sharif.currencyconverter.data.source.Result
import java.lang.Exception

class RatesLocalDataSource: RatesDataSource {

    override suspend fun getRates(base: String): Result<RateList> {

        


        return Result.Error(Exception("Not implemented yet!!"))
    }

}