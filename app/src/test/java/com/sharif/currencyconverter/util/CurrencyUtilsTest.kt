package com.sharif.currencyconverter.util

import androidx.test.platform.app.InstrumentationRegistry
import com.sharif.currencyconverter.R
import org.hamcrest.core.Is.`is`
import org.junit.Test

import org.junit.Assert.*

class CurrencyUtilsTest {


    /**
     * Check if currency provide you perfect Flag and Name
     */
    @Test
    fun getCurrency() {
        var currency = CurrencyUtils.getCurrency("EUR")
        assertEquals(currency?.flag, R.drawable.ic_eur_flag)
        //Try an unknown flag
        currency = CurrencyUtils.getCurrency("EURRREEER")
        assertEquals(currency?.flag, R.drawable.ic_eur_flag)

        //Try another flag
        currency = CurrencyUtils.getCurrency("CAD")
        assertEquals(currency?.flag, R.drawable.ic_cad_flag)
    }
}