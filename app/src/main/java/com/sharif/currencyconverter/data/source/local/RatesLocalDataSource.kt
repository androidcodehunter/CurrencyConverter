package com.sharif.currencyconverter.data.source.local

import com.sharif.currencyconverter.data.db.AppDatabase
import com.sharif.currencyconverter.data.db.entity.RateList
import com.sharif.currencyconverter.data.source.RatesDataSource
import com.sharif.currencyconverter.data.source.Result
import com.sharif.currencyconverter.data.source.Result.Success
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RatesLocalDataSource(private val appDatabase: AppDatabase,
                           private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO): RatesDataSource {

    override suspend fun getRates(base: String): Result<RateList?> = withContext(ioDispatcher){
        return@withContext Success(appDatabase.rateDao().getRates(base))
    }

    override suspend fun insertOrUpdateRates(rateList: RateList) = withContext(ioDispatcher){
        appDatabase.rateDao().insertOrUpdateRates(rateList)
    }

}