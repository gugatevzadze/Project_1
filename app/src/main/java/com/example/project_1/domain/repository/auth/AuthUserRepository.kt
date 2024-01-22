package com.example.project_1.domain.repository.auth

interface AuthUserRepository {
    fun getCurrentUser(): String?
}