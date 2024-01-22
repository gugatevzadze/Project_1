package com.example.project_1.presentation.event.home


sealed class HomeEvent {
    data object OnListButtonClicked : HomeEvent()
    data object Logout : HomeEvent()
}