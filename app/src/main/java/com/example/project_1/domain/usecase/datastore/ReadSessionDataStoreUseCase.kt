package com.example.project_1.domain.usecase.datastore

import com.example.project_1.domain.repository.datastore.DataStoreRepository
import com.example.project_1.domain.user_data_key.SessionKey
import javax.inject.Inject


class ReadSessionDataStoreUseCase @Inject constructor(private val dataStoreRepository: DataStoreRepository) {

    operator fun invoke() = dataStoreRepository.readSession(key = SessionKey.TOKEN)
}
