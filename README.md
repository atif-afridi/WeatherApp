# Jetpack Compose WeatherApp 🌡

WeatherApp is an application that shows you the weather according to your location. You can find out by searching the weather information of the city you want

<br/>

You can enter your API key in the location specified in the gradle.properties file in the root folder.

## Api 📦
* [OpenWeather Call 5 day / 3 hour forecast data](https://openweathermap.org/api)

## Libraries 📚

* [Retrofit](https://square.github.io/retrofit)

* [Location](https://developer.android.com/training/location)

* [ViewModel](https://developer.android.com/jetpack/compose/libraries#viewmodel)

* [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
* 
* [Coil](https://coil-kt.github.io/coil/)

* [Accompanist](https://google.github.io/accompanist/insets/)

## Assets 🖼

* www.google.com

## Outputs 🖼

### Home Screen 🏠

### Search City  🔍

### Error Screens ⚠

## Project Structure 🏗

```
...
composeweatherapp
│
|
└───weapp
|   |   WeatherApplication.kt
|   |   MainActivity.kt
|   |
|   └───theme
|   |   |
|   |   |   Color.kt
|   |   |   Shape.kt
|   |   |   Theme.kt
|   |   |   Type.kt
|
└───core
|   |
|   └───di
|   |   |   NetworkModule.kt
|   |   |   LocationModule.kt
|   |   |   RepositoryModule.kt
|   
└───data
|   |
|   └───location
|   |   |   DefaultLocationTracker.kt
|   |   |
|   └───model
|   |   |   WeatherResponse.kt
|   |   |
|   └───network
|   |   |   WeatherApi.kt
|   |   |
|   └───repository
|   |   |   WeatherRepositoryImpl.kt
|   |   |
|   
└───domain
|   |
|   └───location
|   |   |   LocationTracker.kt
|   |   |
|   └───repository
|   |   |   WeatherRepository.kt
|   |   |
|   └───usecase  
|   |   |   |  
|   |   |   └───location
|   |   |   |   |  GetLocationUseCase.kt
|   
└───presentation
|   |
|   └───components
|   |   |   ForecastComponent.kt
|   |   |   HourlyComponent.kt
|   |   |   WeatherComponent.kt
|   |   |
|   └───dashboard
|   |   |   |  
|   |   |   └───model
|   |   |   |   |  Forecast.kt
|   |   |   |   |  Hour.kt
|   |   |   |   |  Weather.kt
|   |   |   SearchWidgetState.kt
|   |   |   WeatherScreen.kt
|   |   |   WeatherUiState.kt
|   |   |
|   └───usecase  
|   |   |   WeatherViewModel.kt
|   
└───ui
|   |
|   └───theme
|   |   |   Color.kt
|   |   |   Theme.kt
|   |   |   Type.kt
|   | Animation.kt
|   
└───utils
|   |
|   |   |   Constants.kt
|   |   |   DateUtil.kt
|   |   |   Result.kt


```

