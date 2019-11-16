package com.sharif.currencyconverter.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class RateConverter {

    @TypeConverter
    fun fromString(value: String): Map<String, Double>{
        val listType = object : TypeToken<Map<String, Double>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun toString(map: Map<String, Double>): String{
        val gson = Gson()
        return gson.toJson(map)
    }


}