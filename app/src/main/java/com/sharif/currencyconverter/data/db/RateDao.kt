package com.sharif.currencyconverter.data.db

import androidx.room.*
import com.sharif.currencyconverter.data.db.entity.RateList

@Dao
interface RateDao {

    @Query("select * from ratelist where base = :symbol")
    fun getRates(symbol: String): RateList?

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdateRates(rateList: RateList)

}