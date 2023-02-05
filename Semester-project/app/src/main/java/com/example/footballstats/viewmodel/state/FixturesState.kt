package com.example.footballstats.viewmodel.state

import com.example.footballstats.data.remote.models.Data

sealed class FixturesState {
    object Empty : FixturesState()
    object Loading : FixturesState()
    class Success(val data: List<Data>) : FixturesState()
    class Error(val message: String) : FixturesState()
}
