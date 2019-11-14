package com.sharif.currencyconverter.ui.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sharif.currencyconverter.R
import com.sharif.currencyconverter.data.model.Rate
import kotlinx.android.synthetic.main.list_item_currency_converter.view.*

class CurrencyRatesAdapter: ListAdapter<Rate, CurrencyRatesAdapter.RateViewHolder>(CURRENCY_RATES_COMPARATOR) {

    private var amount = 1.0

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

    fun updateAmount(amount: Double){
        this.amount = amount
        notifyItemRangeChanged(0, currentList.size - 1, amount)
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
                            if (!s.isEmpty()){
                                updateAmount(s.toString().toDouble())
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
            val newList = currentList.toMutableList()
            newList.removeAt(currentPosition).also {
                newList.add(0, it)
            }
            submitList(newList)
            notifyItemMoved(currentPosition, 0)
        }

        fun bindTo(rate: Rate) {
            tvCurrencySymbol.text = rate.symbol
            tvCurrencyName.text = "European"
            //Top amount is focused so we dont changed the top amount
            if (!etCurrencyAmount.isFocused){
                etCurrencyAmount.setText((rate.rate * amount).toString())
            }
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