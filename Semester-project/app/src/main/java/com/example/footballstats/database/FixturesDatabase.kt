package com.example.footballstats.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FixtureItem::class], version = 1)
abstract class FixturesDatabase : RoomDatabase() {
    abstract fun todoDao(): FixturesDatabaseDao
    companion object {
        private var INSTANCE: FixturesDatabase? = null
        fun getInstance(context: Context): FixturesDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        FixturesDatabase::class.java,
                        "fixtures_list_database"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}