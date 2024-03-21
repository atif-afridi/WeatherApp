package com.example.weapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weapp.data.location.DefaultLocationTracker
import com.example.weapp.domain.repository.WeatherRepository
import com.example.weapp.domain.usecase.location.GetLocationUseCase
import com.example.weapp.presentation.dashboard.SearchWidgetState
import com.example.weapp.presentation.dashboard.WeatherUiState
import com.example.weapp.utils.DEFAULT_WEATHER_DESTINATION
import com.example.weapp.utils.ExceptionTitles.UNKNOWN_ERROR
import com.example.weapp.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val getCurrentLocation: GetLocationUseCase
) : ViewModel() {

    private val _weatherWidgetState = MutableStateFlow<WeatherUiState>(WeatherUiState.Loading)
    val weatherWidgetState = _weatherWidgetState.asStateFlow()

    private val _searchWidgetState = MutableStateFlow<SearchWidgetState>(SearchWidgetState.CLOSED)
    val searchWidgetState = _searchWidgetState.asStateFlow()

    private val _searchTextState: MutableStateFlow<String> = MutableStateFlow(value = "")
    val searchTextState = _searchTextState.asStateFlow()

    fun updateSearchWidgetState(newValue: SearchWidgetState) {
        _searchWidgetState.value = newValue
    }

    fun updateSearchTextState(newValue: String) {
        _searchTextState.value = newValue
    }

    fun getWeather(city: String = DEFAULT_WEATHER_DESTINATION) {
        repository.getCityWeather(city).map { result ->
            when (result) {
                is Result.Success -> {
                    _weatherWidgetState.value = WeatherUiState.Success(weather = result.data)
                }

                is Result.Error -> {
                    _weatherWidgetState.value =
                        WeatherUiState.Error(errorMessage = result.errorMessage)
                }

                Result.Loading -> {
                    _weatherWidgetState.value = WeatherUiState.Loading
                }
            }
        }.launchIn(viewModelScope)
    }

    fun loadLocation() {
        _weatherWidgetState.value = WeatherUiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val locationData = getCurrentLocation.getLocation()

                if (locationData != null) {
                    // TODO cache the lat/lng
                    repository.getLatLongWeather(locationData.latitude, locationData.longitude)
                        .map { result ->
                            when (result) {
                                is Result.Success -> {
                                    _weatherWidgetState.value =
                                        WeatherUiState.Success(weather = result.data)
                                }

                                is Result.Error -> {
                                    _weatherWidgetState.value =
                                        WeatherUiState.Error(errorMessage = result.errorMessage)
                                }

                                Result.Loading -> {
                                    _weatherWidgetState.value = WeatherUiState.Loading
                                }
                            }
                        }.launchIn(viewModelScope)
                } else {
                    _weatherWidgetState.value = WeatherUiState.Error(errorMessage = UNKNOWN_ERROR)
                }
            } catch (e: Exception) {
                _weatherWidgetState.value = WeatherUiState.Error(errorMessage = e.message)
            }
        }
    }

}