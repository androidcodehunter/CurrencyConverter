package com.sharif.currencyconverter.ui.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sharif.currencyconverter.R
import com.sharif.currencyconverter.data.model.Rate
import com.sharif.currencyconverter.util.CurrencyUtils
import kotlinx.android.synthetic.main.list_item_currency_converter.view.*

class CurrencyRatesAdapter(val onAmountUpdate: (String, Double) -> Unit) :
    RecyclerView.Adapter<CurrencyRatesAdapter.RateViewHolder>() {

    private var symbolAndRates = mutableMapOf<String, Rate>()
    private val currencies = mutableListOf<String>()
    private var amount = 1.0

    fun submitList(list: MutableList<Rate>?) {
        //Update the rates based on symbol
        list?.forEach {
            symbolAndRates[it.symbol] = it
        }

        if (currencies.isEmpty()){
            list?.let { rates ->
                currencies.addAll(rates.map { it.symbol})
            }
        }

        list?.let {
            notifyItemRangeChanged(0, it.size.minus(1), amount)
        }

    }

    override fun getItemCount() = currencies.size

    fun getItem(position: Int): Rate?{
        return symbolAndRates.get(currencies.get(position))
    }


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
            holder.bindTo(getItem(position)!!)
        }
    }

    override fun onBindViewHolder(holder: RateViewHolder, position: Int) {
        holder.bindTo(getItem(position)!!)
    }

    fun updateAmount(amount: Double){
        this.amount = amount

        /*
        * TODO Immediate update currency
        * */
        notifyItemRangeChanged(1, currencies.size - 1, amount)
    }

    inner class RateViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val ivCurrencySymbol = itemView.ivCurrencySymbol
        private val tvCurrencySymbol = itemView.tvTitle
        private val tvCurrencyName = itemView.tvDescription
        private val etCurrencyAmount = itemView.etCurrencyAmount

        init {
            etCurrencyAmount.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus){
                    layoutPosition.takeIf { it > 0 }?.also { position ->
                        moveSelectedListItemToTop(position)
                        etCurrencyAmount.setSelection(etCurrencyAmount.text?.length ?: 0)
                    }
                }
            }

            etCurrencyAmount.addTextChangedListener(object : TextWatcher{
                override fun afterTextChanged(s: Editable?) {}
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (etCurrencyAmount.isFocused){
                        s?.let {
                            if (s.isNotEmpty()){
                                onAmountUpdate(getItem(adapterPosition)!!.symbol, s.toString().toDouble())
                            }
                        }
                    }
                }

            })

            itemView.setOnClickListener {
               etCurrencyAmount.requestFocus()
            }
        }

        /**
         * Move current position item to top of the RecyclerView.
         * @param currentPosition current selected position.
         */
        private fun moveSelectedListItemToTop(currentPosition: Int) {
            currencies.removeAt(currentPosition).also {
                currencies.add(0, it)
            }
            ///submitList(newList)
            notifyItemMoved(currentPosition, 0)
        }

        fun bindTo(rate: Rate) {
            val currency = CurrencyUtils.getCurrency(rate.symbol)
            currency?.apply {
                ivCurrencySymbol.setImageResource(flag)
                tvCurrencyName.text = name
            }

            tvCurrencySymbol.text = rate.symbol
            //Top amount is focused so we dont changed the top amount
            if (!etCurrencyAmount.isFocused){
                etCurrencyAmount.setText((rate.rate * amount).toString())
            }
        }
    }

}