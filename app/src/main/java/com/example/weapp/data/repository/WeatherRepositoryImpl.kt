package com.example.weapp.data.repository

import com.example.weapp.data.model.toWeather
import com.example.weapp.data.network.WeatherApi
import com.example.weapp.domain.repository.WeatherRepository
import com.example.weapp.presentation.dashboard.model.Weather
import com.example.weapp.utils.ExceptionTitles.NO_INTERNET_CONNECTION
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import com.example.weapp.utils.Result
import javax.inject.Inject


class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : WeatherRepository {

    override fun getCityWeather(city: String): Flow<Result<Weather>> = flow {
        emit(Result.Loading)
        try {
            val result = weatherApi.getWeather(city = city).toWeather()
            emit(Result.Success(result))
        } catch (exception: HttpException) {
            emit(Result.Error(exception.message.orEmpty()))
        } catch (exception: IOException) {
            emit(Result.Error(NO_INTERNET_CONNECTION))
        }
    }.flowOn(dispatcher)

    override fun getLatLongWeather(
        latitude: Double,
        longitude: Double
    ) = flow {
        emit(Result.Loading)
        try {
            val query = "$latitude,$longitude"
            val result = weatherApi.getLatLongWeather(query = query).toWeather()
            emit(Result.Success(result))
        } catch (exception: HttpException) {
            emit(Result.Error(exception.message.orEmpty()))
        } catch (exception: IOException) {
            emit(Result.Error(NO_INTERNET_CONNECTION))
        }
    }.flowOn(dispatcher)
}
