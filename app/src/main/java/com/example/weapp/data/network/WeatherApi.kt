package com.example.weapp.data.network

import com.example.weapp.BuildConfig
import com.example.weapp.data.model.WeatherResponse
import com.example.weapp.utils.DEFAULT_WEATHER_DESTINATION
import com.example.weapp.utils.NUMBER_OF_DAYS
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("forecast.json")
    suspend fun getWeather(
        @Query("key") key: String = BuildConfig.API_KEY,
        @Query("q") city: String = DEFAULT_WEATHER_DESTINATION,
        @Query("days") days: Int = NUMBER_OF_DAYS,
    ): WeatherResponse

    @GET("forecast.json")
    suspend fun getLatLongWeather(
        @Query("key") key: String = BuildConfig.API_KEY,
        @Query("q") query: String = DEFAULT_WEATHER_DESTINATION,
        @Query("days") days: Int = NUMBER_OF_DAYS,
    ): WeatherResponse
}