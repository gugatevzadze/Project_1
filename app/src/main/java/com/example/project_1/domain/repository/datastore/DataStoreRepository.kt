package com.example.project_1.domain.repository.datastore


import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    suspend fun clear()

    fun readSession(key: Preferences.Key<Boolean>): Flow<Boolean>

    suspend fun saveSession(key: Preferences.Key<Boolean>, value: Boolean)

}