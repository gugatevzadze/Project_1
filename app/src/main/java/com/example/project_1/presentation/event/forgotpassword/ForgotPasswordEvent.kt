package com.example.project_1.presentation.event.forgotpassword

sealed class ForgotPasswordEvent {
    data class SendPasswordLink(val email: String) : ForgotPasswordEvent()
    data object ResetErrorMessage : ForgotPasswordEvent()
}