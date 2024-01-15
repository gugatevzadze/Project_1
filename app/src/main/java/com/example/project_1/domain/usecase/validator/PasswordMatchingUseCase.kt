package com.example.project_1.domain.usecase.validator

class PasswordMatchingUseCase {
    fun passwordsMatch(passwordOne: String, passwordTwo: String): Boolean {
        return passwordOne == passwordTwo
    }
}