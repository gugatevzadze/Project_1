package com.example.project_1.presentation.state.forgotpassword

import com.example.project_1.data.common.Resource

data class ForgotPasswordState(
    val state: Resource<Unit>?,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)