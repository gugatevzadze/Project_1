package com.example.project_1.data.common

import kotlinx.coroutines.flow.flow

class AuthResponseHandler {
    suspend fun <T : Any> safeAuthenticationCall(call: suspend () -> T) = flow {
        emit(Resource.Loading(loading = true))
        try {
            val result = call()
            emit(Resource.Success(data = result))
        } catch (e: Exception) {
            emit(Resource.Error(errorMessage = e.message ?: "Error Occurred"))
        }
        emit(Resource.Loading(loading = false))
    }
}