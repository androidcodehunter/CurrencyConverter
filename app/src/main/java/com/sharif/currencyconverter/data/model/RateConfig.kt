package com.sharif.currencyconverter.data.model

import com.sharif.currencyconverter.ui.home.RatesConverterViewModel.Companion.ONE_SECOND_IN_MILLIS

data class RateConfig(val rate: Rate,
                      val times: Int = Integer.MAX_VALUE,
                      val delayTime: Long = ONE_SECOND_IN_MILLIS)