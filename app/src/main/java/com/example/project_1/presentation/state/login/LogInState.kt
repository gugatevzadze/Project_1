package com.example.project_1.presentation.state.login

data class LogInState(
    val isLoading: Boolean = false,
    val accessToken: String? = null,
    val errorMessage: String? = null
)