package com.sharif.currencyconverter.util

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

class PreferenceUtil (context: Context){

    val prefs = PreferenceManager.getDefaultSharedPreferences(context)

    /**
     * Get preference value using key
     * @param  key
     * @param defaultValue
     * */
    fun getString(key: String, defaultValue: String): String? {
        return prefs.getString(key, defaultValue)
    }

    /**
     * Set preference string
     * @param  key
     * @param value
     * */
    fun setString(key: String, value: String){
        getEditor().putString(key, value).commit()
    }

    /**
     * Editor to write into SharedPreference file
     */
    private fun getEditor(): SharedPreferences.Editor {
        return prefs.edit()
    }

}