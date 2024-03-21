package com.example.weapp.presentation.dashboard

import com.example.weapp.presentation.dashboard.model.Weather


sealed interface WeatherUiState {
    data class Success(val weather: Weather?) : WeatherUiState
    data class Error(val errorMessage: String?) : WeatherUiState

    object Loading : WeatherUiState
}


