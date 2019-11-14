package com.sharif.currencyconverter.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sharif.currencyconverter.R
import com.sharif.currencyconverter.data.model.Rate
import com.sharif.currencyconverter.data.model.RateList
import com.sharif.currencyconverter.data.source.Result
import com.sharif.currencyconverter.ui.adapter.CurrencyRatesAdapter
import kotlinx.android.synthetic.main.fragment_converter.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class FragmentConverter: Fragment() {

    private lateinit var currencyRatesAdapter: CurrencyRatesAdapter
    private val ratesViewModel: RatesConverterViewModel by viewModel()

    private val ratesObserver = androidx.lifecycle.Observer<Result<RateList?>>{
        if (it is Result.Loading){
            showLoading()
        } else if (it is Result.Success){
            hideLoading()
            //Convert to rates
            val rates = it.data?.rates?.map { Rate(it.key, it.value) }
            currencyRatesAdapter.submitList(rates)
            Timber.d("${rates?.size}")
        }else if (it is Result.Error){
            hideLoading()
        }
    }

    private fun showLoading() {
        loading.visibility = View.VISIBLE
    }

    private fun hideLoading(){
        loading.visibility = View.GONE
    }

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


        ratesViewModel.getRates("EUR").observe(viewLifecycleOwner, ratesObserver)

       /// currencyRatesAdapter.submitList(ratesViewModel.getRates())
    }


    companion object{
        fun newInstance(): FragmentConverter {
            return FragmentConverter()
        }
    }
}