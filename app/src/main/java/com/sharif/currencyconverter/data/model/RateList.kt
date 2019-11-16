package com.sharif.currencyconverter.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RateList(@PrimaryKey val base: String, val date: String, val rates: Map<String, Double>)