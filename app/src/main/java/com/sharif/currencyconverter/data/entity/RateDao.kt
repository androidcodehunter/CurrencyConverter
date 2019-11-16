package com.sharif.currencyconverter.data.entity

import androidx.room.*

@Dao
interface RateDao {

    @Query("select * from ratelist where base = :symbol")
    fun getRates(symbol: String): RateList?

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdateRates(rateList: RateList)

}