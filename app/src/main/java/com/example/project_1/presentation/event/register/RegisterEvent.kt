package com.example.project_1.presentation.event.register

import com.example.project_1.presentation.event.login.LogInEvent

sealed class RegisterEvent {
    data class Register(val email: String, val password: String, val confirmPassword: String) : RegisterEvent()
//    data object UserAlreadyExist : RegisterEvent()
    data object ResetErrorMessage : RegisterEvent()
}