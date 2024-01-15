package com.example.project_1.presentation.event.welcome

sealed class WelcomeEvent {
    data object LoginButtonClicked : WelcomeEvent()
    data object RegisterButtonClicked : WelcomeEvent()
}