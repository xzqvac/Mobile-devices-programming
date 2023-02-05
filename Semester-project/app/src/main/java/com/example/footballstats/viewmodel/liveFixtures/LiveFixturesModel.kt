package com.example.footballstats.viewmodel.liveFixtures

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.footballstats.repository.FixturesRepository
import com.example.footballstats.viewmodel.state.FixturesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class LiveFixturesModel @Inject constructor(private val fixturesRepository: FixturesRepository): ViewModel() {

    private val _liveFixturesState = MutableStateFlow<FixturesState>(FixturesState.Empty)
    private val liveFixturesState: StateFlow<FixturesState> = _liveFixturesState

    private val _futureFixturesState = MutableStateFlow<FixturesState>(FixturesState.Empty)
    private val futureFixturesState: StateFlow<FixturesState> = _futureFixturesState

    fun getLiveFixtures() {
        _liveFixturesState.value = FixturesState.Loading

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val liveFixturesResponse = fixturesRepository.getLiveFixtures()
                _liveFixturesState.value = FixturesState.Success(liveFixturesResponse)
            }
            catch (exception: HttpException) {
                _liveFixturesState.value = FixturesState.Error("Something went wrong")
            }
            catch (exception: IOException) {
                _liveFixturesState.value = FixturesState.Error("No internet connection")
            }
        }
    }

    fun getFutureFixtures() {
        _futureFixturesState.value = FixturesState.Loading

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val liveFixturesResponse = fixturesRepository.getFutureFixtures()
                _futureFixturesState.value = FixturesState.Success(liveFixturesResponse)
            }
            catch (exception: HttpException) {
                _futureFixturesState.value = FixturesState.Error("Something went wrong")
            }
            catch (exception: IOException) {
                _futureFixturesState.value = FixturesState.Error("No internet connection")
            }
        }
    }

}