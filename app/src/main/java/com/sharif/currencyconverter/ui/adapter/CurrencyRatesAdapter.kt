package com.sharif.currencyconverter.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sharif.currencyconverter.R
import com.sharif.currencyconverter.data.model.Rate
import kotlinx.android.synthetic.main.list_item_currency_converter.view.*
import timber.log.Timber

class CurrencyRatesAdapter: ListAdapter<Rate, CurrencyRatesAdapter.RateViewHolder>(CURRENCY_RATES_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RateViewHolder {
        return RateViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.list_item_currency_converter, parent, false))
    }

    override fun onBindViewHolder(
        holder: RateViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()){
            super.onBindViewHolder(holder, position, payloads)
        } else{
            holder.bindTo(getItem(position))
        }
    }

    override fun onBindViewHolder(holder: RateViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    inner class RateViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val ivCurrencySymbol = itemView.ivCurrencySymbol
        private val tvCurrencySymbol = itemView.tvTitle
        private val tvCurrencyName = itemView.tvDescription
        private val etCurrencyAmount = itemView.etCurrencyAmount

        init {
            etCurrencyAmount.setOnFocusChangeListener { view, hasFocus ->
                if (hasFocus){
                    layoutPosition.takeIf { it > 0 }?.also {
                        Timber.d("Current Position %s", it)
                    }
                }
            }
        }

        fun bindTo(rate: Rate) {
            tvCurrencySymbol.text = rate.symbol
            tvCurrencyName.text = "European"
            etCurrencyAmount.setText(rate.rate.toString())
        }
    }

    companion object{

        /**
         * Currency comparator to check for new data updates only, it will ignore duplicate data update.
         * This technique is very effective when you update data continuously.
         */
        private val CURRENCY_RATES_COMPARATOR = object : DiffUtil.ItemCallback<Rate>() {
            override fun areItemsTheSame(oldItem: Rate, newItem: Rate): Boolean {
                return oldItem.symbol == newItem.symbol
            }

            override fun areContentsTheSame(oldItem: Rate, newItem: Rate): Boolean {
                return oldItem == newItem
            }
        }
    }
}