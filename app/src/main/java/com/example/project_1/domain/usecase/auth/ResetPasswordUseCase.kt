package com.example.project_1.domain.usecase.auth

import com.example.project_1.domain.repository.auth.AuthRepository
import javax.inject.Inject

class ResetPasswordUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(email: String) =
        authRepository.passwordReset(email)
}