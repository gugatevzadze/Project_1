package com.example.project_1.data.repository.auth

import com.example.project_1.domain.repository.auth.AuthUserRepository
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class AuthUserRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
): AuthUserRepository {
    override fun getCurrentUser(): String? {
        return firebaseAuth.currentUser?.uid
    }
}