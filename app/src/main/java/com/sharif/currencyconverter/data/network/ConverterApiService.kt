package com.sharif.currencyconverter.data.network

import com.sharif.currencyconverter.data.model.RateList
import com.sharif.currencyconverter.data.source.Result
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ConverterApiService {

    @GET("/latest")
    suspend fun getTopTracks(@Query("base") base: String): Response<RateList>

    companion object{
        const val BASE_URL = "https://revolut.duckdns.org"
    }

}