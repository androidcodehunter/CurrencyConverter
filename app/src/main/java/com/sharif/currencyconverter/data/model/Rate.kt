package com.sharif.currencyconverter.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Rate(val symbol: String, val rate: Double): Parcelable