package com.example.footballstats.database

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FixturesViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData: LiveData<List<FixtureItem>>
    private val repository: FixturesRepositoryDB

    init {
        val todoDao = FixturesDatabase.getInstance(application).todoDao()
        repository = FixturesRepositoryDB(todoDao)
        readAllData = repository.readAllData
    }

    fun addTodo(fixtureItem: FixtureItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addFixture(fixtureItem)
        }
    }

/*    fun deleteTodo(fixtureItem: FixtureItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.d(todoItem = todoItem)
        }
    }*/
}
