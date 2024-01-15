package com.example.project_1.domain.usecase.auth

import com.example.project_1.domain.repository.auth.AuthRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(private val authRepository: AuthRepository){
    suspend operator fun invoke(email: String, password: String) =
        authRepository.register(email = email, password = password)
}