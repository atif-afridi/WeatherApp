package com.example.weapp.presentation.dashboard

sealed class SearchWidgetState {
    object OPENED : SearchWidgetState()
    object CLOSED : SearchWidgetState()
}