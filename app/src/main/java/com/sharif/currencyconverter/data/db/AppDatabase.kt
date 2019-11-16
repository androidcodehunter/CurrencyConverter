package com.sharif.currencyconverter.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sharif.currencyconverter.data.db.entity.RateList

@Database(entities = [RateList::class], version = 1, exportSchema = false)
@TypeConverters(RateConverter::class)
abstract class AppDatabase: RoomDatabase() {

    abstract fun rateDao(): RateDao

    companion object{
        @Volatile private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance
            ?: synchronized(LOCK){
            buildDatabase(context)
                .also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
            AppDatabase::class.java, "currency.db").build()
    }

}