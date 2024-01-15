package com.example.project_1.domain.model.auth

data class GetUser(
    private val email: String,
    private val password: String
)