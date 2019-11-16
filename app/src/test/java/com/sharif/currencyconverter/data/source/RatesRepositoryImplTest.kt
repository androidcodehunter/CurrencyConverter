package com.sharif.currencyconverter.data.source

import android.app.Application
import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import com.sharif.currencyconverter.ConverterApplication
import com.sharif.currencyconverter.data.db.entity.RateList
import com.sharif.currencyconverter.di.converterRepositoryModule
import com.sharif.currencyconverter.di.netWorkModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject
import org.mockito.Mockito.mock
import java.net.UnknownHostException

class RatesRepositoryImplTest: AutoCloseKoinTest(){


    val ratesRepository: RatesRepositoryImpl by inject()

    @get:Rule
    var expectedException = ExpectedException.none()

    @Before
    fun before(){

        startKoin {
            androidContext(mock(Context::class.java))
            modules(listOf(netWorkModule,
                converterRepositoryModule))
        }
    }


    @ExperimentalCoroutinesApi
    @Test
    fun getRates_requestAllRatesFromRemoteDataSourceAndCheckException() = runBlocking {
        // get rates for EUR
        val response = ratesRepository.getRates("EUR", true)
        verifyResponse(response)
        //Now verify response with unknown symbol
        val unknownResponse = ratesRepository.getRates("EURRRRRRRRRR", true)
        verifyResponse(unknownResponse)
    }

    private fun verifyResponse(response: Result<RateList?>) {
        if (response is Result.Success) {
            print(response)
            //Check if coming data is not empty when successful
            assertEquals(true, !(response.data?.rates?.isEmpty() ?: true))
        } else if (response is Result.Error) {
            print(response)
            //If no internet connection available
            if (response.exception is UnknownHostException) {
                expectedException.expect(UnknownHostException::class.java)
            } else {
                expectedException.expect(Exception::class.java)
            }
            throw response.exception
        }
    }

}