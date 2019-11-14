package com.sharif.currencyconverter.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sharif.currencyconverter.R
import com.sharif.currencyconverter.ui.adapter.CurrencyRatesAdapter
import kotlinx.android.synthetic.main.fragment_converter.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class FragmentConverter: Fragment() {

    private lateinit var currencyRatesAdapter: CurrencyRatesAdapter
    private val ratesViewModel: RatesConverterViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_converter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currencyRatesAdapter = CurrencyRatesAdapter()
        rvRatesConverter.apply {
            adapter = currencyRatesAdapter
        }

        currencyRatesAdapter.submitList(ratesViewModel.getRates())
    }


    companion object{
        fun newInstance(): FragmentConverter {
            return FragmentConverter()
        }
    }
}