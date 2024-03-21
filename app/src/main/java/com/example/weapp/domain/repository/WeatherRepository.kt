package com.example.weapp.domain.repository

import com.example.weapp.presentation.dashboard.model.Weather
import kotlinx.coroutines.flow.Flow
import com.example.weapp.utils.Result

interface WeatherRepository {

    fun getCityWeather(city: String): Flow<Result<Weather>>

    fun getLatLongWeather(latitude: Double, longitude: Double): Flow<Result<Weather>>

}