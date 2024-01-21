package com.example.project_1.domain.usecase.datastore

import android.util.Log
import com.example.project_1.domain.repository.datastore.DataStoreRepository
import com.example.project_1.domain.user_data_key.SessionKey
import javax.inject.Inject


class SaveSessionDataStoreUseCase @Inject constructor(private val dataStoreRepository: DataStoreRepository) {
    suspend operator fun invoke(value: Boolean) {
        dataStoreRepository.saveSession(key = SessionKey.TOKEN, value = value)
    }
}

