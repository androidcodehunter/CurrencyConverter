package com.sharif.currencyconverter.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.sharif.currencyconverter.data.model.RateList
import java.lang.Exception

class RatesRepositoryImpl(private val ratesLocalDataSource: RatesDataSource,
                          private val ratesRemoteDataSource: RatesDataSource): RatesRepository {

/*    override suspend fun getRates(base: String, forceUpdate: Boolean): LiveData<Result<RateList?>> = liveData{
        emit(Result.Loading)
        val savedRates = ratesLocalDataSource.getRates(base)

        savedRates?.let {
            emit(Result.Success(savedRates))
        }

        if (forceUpdate){
            repeat(3443){
                emit(updateRatesFromRemoteDataSource(base))
                delay(1000)
            }
        }
    }*/

/*
    suspend fun getRates(base: String) = liveData{


    }*/


    override suspend fun getRates(base: String, forceUpdate: Boolean): Result<RateList?> {
        try {
            updateRatesFromRemoteDataSource(base)
        }catch (ex: Exception){
            return Result.Error(ex)
        }

        return getSavedRates(base)
    }

    override suspend fun getSavedRates(base: String): Result<RateList?> {
        return ratesLocalDataSource.getRates(base)
    }



/*    private suspend fun updateRatesFromRemoteDataSource(base: String): Result<RateList?>{
        return try {
            val remoteResponse = ratesRemoteDataSource.getRates(base)
            Result.Success(remoteResponse)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }*/



    private suspend fun updateRatesFromRemoteDataSource(base: String) {
        val response = ratesRemoteDataSource.getRates(base)
        if (response is Result.Success){
            response.data?.let {
                ratesLocalDataSource.insertOrUpdateRates(it)
            }
        }else if (response is Result.Error){
            throw response.exception
        }
    }

}