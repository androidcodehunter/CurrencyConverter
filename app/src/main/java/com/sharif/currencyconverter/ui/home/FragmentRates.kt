package com.sharif.currencyconverter.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sharif.currencyconverter.R
import com.sharif.currencyconverter.data.model.Rate
import com.sharif.currencyconverter.ui.adapter.CurrencyRatesAdapter
import kotlinx.android.synthetic.main.fragment_rates.*

class FragmentRates: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_rates, container, false)
    }


    companion object{
        fun newInstance(): FragmentRates {
            return FragmentRates()
        }
    }
}