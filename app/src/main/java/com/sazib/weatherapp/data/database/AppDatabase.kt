package com.sazib.weatherapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sazib.weatherapp.BuildConfig

@Database(
    // entities = [(CityList::class), (CityListService::class)],
    entities = [],
    version = BuildConfig.VERSION_CODE,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

/*  abstract fun CityListDao(): CityListDao

  abstract fun CityListServiceDao():CityListServiceDao*/

}