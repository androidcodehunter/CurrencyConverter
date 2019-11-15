package com.sharif.currencyconverter.util

import com.sharif.currencyconverter.R
import com.sharif.currencyconverter.data.model.Currency

object CurrencyUtils {

    private val CURRENCIES = hashMapOf<String, Currency>()
    init {
        CURRENCIES.put("EUR", Currency("Euro", R.drawable.ic_eur_flag))
        CURRENCIES.put("AUD", Currency("Australian dollar", R.drawable.ic_aud_flag))
        CURRENCIES.put("BGN", Currency("Bulgarian lev", R.drawable.ic_bgn_flag))
        CURRENCIES.put("BRL", Currency("Brazilian real", R.drawable.ic_brl_flag))
        CURRENCIES.put("CAD", Currency("Canadian dollar", R.drawable.ic_cad_flag))
        CURRENCIES.put("CHF", Currency("Swiss franc", R.drawable.ic_chf_flag))
        CURRENCIES.put("CNY", Currency("Chinese yuan", R.drawable.ic_cny_flag))
        CURRENCIES.put("CZK", Currency("Czech koruna", R.drawable.ic_czk_flag))
        CURRENCIES.put("DKK", Currency("Danish krone", R.drawable.ic_dkk_flag))
        CURRENCIES.put("GBP", Currency("British pound", R.drawable.ic_gbp_flag))
        CURRENCIES.put("HKD", Currency("Hong Kong dollar", R.drawable.ic_hkd_flag))
        CURRENCIES.put("HRK", Currency("Croatian kuna", R.drawable.ic_hrk_flag))
        CURRENCIES.put("HUF", Currency("Hungarian forint", R.drawable.ic_huf_flag))
        CURRENCIES.put("IDR", Currency("Indonesian rupiah", R.drawable.ic_idr_flag))
        CURRENCIES.put("ILS", Currency("Israeli new shekel", R.drawable.ic_ils_flag))
        CURRENCIES.put("INR", Currency("Indian rupee", R.drawable.ic_inr_flag))
        CURRENCIES.put("ISK", Currency("Icelandic króna", R.drawable.ic_isk_flag))
        CURRENCIES.put("JPY", Currency("Japanese yen", R.drawable.ic_jpy_flag))
        CURRENCIES.put("KRW", Currency("South Korean won", R.drawable.ic_krw_flag))
        CURRENCIES.put("MXN", Currency("Mexican peso", R.drawable.ic_mxn_flag))
        CURRENCIES.put("MYR", Currency("Malaysian ringgit", R.drawable.ic_myr_flag))
        CURRENCIES.put("NOK", Currency("Norwegian krone", R.drawable.ic_nok_flag))
        CURRENCIES.put("NZD", Currency("New Zealand dollar", R.drawable.ic_nzd_flag))
        CURRENCIES.put("PHP", Currency("Philippine piso", R.drawable.ic_php_flag))
        CURRENCIES.put("PLN", Currency("Polish złoty", R.drawable.ic_pln_flag))
        CURRENCIES.put("RON", Currency("Romanian leu", R.drawable.ic_ron_flag))
        CURRENCIES.put("RUB", Currency("Russian ruble", R.drawable.ic_rub_flag))
        CURRENCIES.put("SEK", Currency("Swedish krona", R.drawable.ic_sek_flag))
        CURRENCIES.put("SGD", Currency("Singapore dollar", R.drawable.ic_sgd_flag))
        CURRENCIES.put("THB", Currency("Thai baht", R.drawable.ic_thb_flag))
        CURRENCIES.put("TRY", Currency("Turkish lira", R.drawable.ic_try_flag))
        CURRENCIES.put("USD", Currency("United States dollar", R.drawable.ic_usd_flag))
        CURRENCIES.put("ZAR", Currency("South African rand", R.drawable.ic_zar_flag))
    }

    fun getCurrency(key: String): Currency? {
        if (CURRENCIES[key.trim().toUpperCase()] == null){
            return CURRENCIES["EUR"]
        }
        return CURRENCIES[key.trim().toUpperCase()]
    }
}