package com.example.project_1.domain.usecase.datastore

import com.example.project_1.domain.repository.datastore.DataStoreRepository
import javax.inject.Inject

class ClearSessionDataStoreUseCase @Inject constructor(private val dataStoreRepository: DataStoreRepository) {
    suspend operator fun invoke() =
        dataStoreRepository.clear()
}