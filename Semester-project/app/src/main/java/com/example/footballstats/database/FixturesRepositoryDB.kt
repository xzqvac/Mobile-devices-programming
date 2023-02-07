package com.example.footballstats.database

import androidx.lifecycle.LiveData

class FixturesRepositoryDB(private val fixturesDatabaseDao: FixturesDatabaseDao) {
    val readAllData : LiveData<List<FixtureItem>> =  fixturesDatabaseDao.getAll()
    suspend fun addFixture(todoItem: FixtureItem) {
        fixturesDatabaseDao.insert(todoItem)
    }
}