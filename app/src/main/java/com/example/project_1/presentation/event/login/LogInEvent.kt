package com.example.project_1.presentation.event.login

sealed class LogInEvent {
    data class LogIn(val email: String, val password: String) : LogInEvent()
    data class LogInWithRememberMe(val email: String, val password: String) : LogInEvent()
//    data object UserDoesNotExist : LogInEvent()
    data object ResetErrorMessage : LogInEvent()
}