package com.example.weapp.presentation.dashboard.model

import com.example.weapp.data.model.WeatherResponse.Current.Condition

data class Weather(
    val temperature: Int,
    val date: String,
    val wind: Int,
    val humidity: Int,
    val feelsLike: Int,
    val condition: Condition,
    val uv: Int,
    val name: String,
    val forecasts: List<Forecast>
)
