package com.sharif.currencyconverter.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.SimpleItemAnimator
import com.sharif.currencyconverter.R
import com.sharif.currencyconverter.data.model.Rate
import com.sharif.currencyconverter.data.db.entity.RateList
import com.sharif.currencyconverter.data.source.Result
import com.sharif.currencyconverter.ui.adapter.CurrencyRatesAdapter
import com.sharif.currencyconverter.util.PreferenceUtil
import kotlinx.android.synthetic.main.fragment_converter.*
import kotlinx.android.synthetic.main.network_error_layout.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import java.util.*

class FragmentConverter: Fragment() {

    private lateinit var currencyRatesAdapter: CurrencyRatesAdapter
    private val ratesViewModel: RatesConverterViewModel by viewModel()
    private lateinit var preference: PreferenceUtil
    private lateinit var lastUpdateText: String

    private val ratesObserver = androidx.lifecycle.Observer<Result<RateList?>>{
        if (it is Result.Loading){
            hideNetworkError()
            showLoading()
        } else if (it is Result.Success){
            //Timber.d("Success $it")
            hideNetworkError()
            hideLoading()
            //Convert to rates
            val rates = it.data?.rates?.map { Rate(it.key, it.value) }?.toMutableList()
            rates?.add(0, Rate(it.data.base, 1.0))
            currencyRatesAdapter.submitList(rates)

            it.data?.date?.let {date ->
                val c = Calendar.getInstance()
                preference.setString(KEY_LAST_UPDATE_TIME, "$date, ${c.get(Calendar.HOUR)}h : ${c.get(Calendar.MINUTE)}m : ${c.get(Calendar.SECOND)}s")
            }

            Timber.d("${rates?.size}")
        }else{
            Timber.d("Error")
            showNetWorkError()
            hideLoading()
        }
    }

    private val amountObserver = androidx.lifecycle.Observer<Double>{
        currencyRatesAdapter.updateAmount(it)
    }

    @SuppressLint("SetTextI18n")
    private fun showNetWorkError() {
        networkErrorContainer.visibility = View.VISIBLE
        val lastUpdateTime = preference.getString(KEY_LAST_UPDATE_TIME, "")
        if (!lastUpdateTime.isNullOrEmpty()){
            if (currencyRatesAdapter.itemCount > 0){
                tvLastUpdateTime.visibility = View.VISIBLE
                tvLastUpdateTime.text = "$lastUpdateText $lastUpdateTime"
            }else{
                tvLastUpdateTime.visibility = View.GONE
            }
        }
    }

    private fun hideNetworkError(){
        networkErrorContainer.visibility = View.GONE
    }

    private fun showLoading() {
        loading.visibility = View.VISIBLE
    }

    private fun hideLoading(){
        loading.visibility = View.GONE
        rvRatesConverter.visibility = View.VISIBLE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preference = PreferenceUtil(context!!)
        lastUpdateText = getString(R.string.last_updated)
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

        currencyRatesAdapter = CurrencyRatesAdapter {
                symbol, updatedAmount ->ratesViewModel.setRates(symbol, updatedAmount, forceUpdate = true)
        }

        rvRatesConverter.apply {
            adapter = currencyRatesAdapter
            setHasFixedSize(true)
            ///(itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        }
        ratesViewModel.getRates().observe(viewLifecycleOwner, ratesObserver)
        ratesViewModel.getAmount().observe(viewLifecycleOwner, amountObserver)
        Timber.d("onViewCreated ${savedInstanceState?.getString(KEY_CURRENCY)}")

        if (savedInstanceState?.getString(KEY_CURRENCY) == null){
            ratesViewModel.setRates(preference.getString(KEY_CURRENCY, DEFAULT_CURRENCY)!!, forceUpdate = true)
        }else{
            ratesViewModel.setRates(savedInstanceState.getString(KEY_CURRENCY)!!, forceUpdate = true)
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(KEY_CURRENCY, "AUD")
        Timber.d("onSaveInstanceState")
    }


    companion object{
        const val KEY_CURRENCY = "key_currency"
        const val DEFAULT_CURRENCY = "EUR"
        const val KEY_LAST_UPDATE_TIME = "key_last_update_time"

        fun newInstance(): FragmentConverter {
            return FragmentConverter()
        }
    }
}