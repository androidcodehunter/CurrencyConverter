package com.sharif.currencyconverter.data.source

import com.sharif.currencyconverter.data.model.RateList
import com.sharif.currencyconverter.di.converterRepositoryModule
import com.sharif.currencyconverter.di.netWorkModule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.koin.core.context.startKoin
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject
import java.net.UnknownHostException

class RatesRepositoryImplTest: AutoCloseKoinTest(){

    val ratesRepository: RatesRepositoryImpl by inject()

    @get:Rule
    var expectedException = ExpectedException.none()

    @Before
    fun before(){
        startKoin {
            modules(listOf(netWorkModule,
                converterRepositoryModule))
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getRates_requestAllRatesFromRemoteDataSourceAndCheckException() = runBlocking {
        // get rates for EUR
        val response = ratesRepository.getRates("EUR")
        verifyResponse(response)
        //Now verify response with unknown symbol
        val unknownResponse = ratesRepository.getRates("EURRRRRRRRRR")
        verifyResponse(unknownResponse)
    }

    private fun verifyResponse(response: Result<RateList?>) {
        if (response is Result.Success) {
            print(response)
            //Check if coming data is not empty when successful
            assertEquals(true, !(response.data?.rates?.isEmpty() ?: true))
        } else if (response is Result.Error) {
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