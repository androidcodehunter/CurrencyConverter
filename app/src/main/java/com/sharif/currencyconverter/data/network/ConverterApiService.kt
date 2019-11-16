package com.sharif.currencyconverter.data.network

import com.sharif.currencyconverter.data.entity.RateList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ConverterApiService {

    @GET("/latest")
    suspend fun getRateList(@Query("base") base: String): Response<RateList>

    companion object{
        const val BASE_URL = "https://revolut.duckdns.org"
    }

}