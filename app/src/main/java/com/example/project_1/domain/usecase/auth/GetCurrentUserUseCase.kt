package com.example.project_1.domain.usecase.auth

import com.example.project_1.domain.repository.auth.AuthUserRepository
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(
    private val authUserRepository: AuthUserRepository
) {
    operator fun invoke() =
        authUserRepository.getCurrentUser(
)
}