package com.sharif.currencyconverter.di

import com.sharif.currencyconverter.data.network.ConverterApiService
import com.sharif.currencyconverter.data.network.ConverterApiService.Companion.BASE_URL
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val netWorkModule = module {
    factory { provideConverterApi(get()) }
    factory { provideOkHttpClient() }
    single { provideRetrofit(get()) }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create()).build()
}

fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient().newBuilder().build()
}

fun provideConverterApi(retrofit: Retrofit): ConverterApiService = retrofit.create(ConverterApiService::class.java)