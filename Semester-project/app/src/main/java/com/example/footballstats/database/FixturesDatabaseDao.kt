package com.example.footballstats.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FixturesDatabaseDao {
    @Query("SELECT * from fixtures")
    fun getAll(): LiveData<List<FixtureItem>>

    @Query("SELECT * from fixtures where itemId = :id")
    fun getById(id: Int) : FixtureItem?

    @Insert
    suspend fun insert(item:FixtureItem)
}