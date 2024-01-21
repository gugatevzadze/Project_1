package com.example.project_1.domain.usecase.plant

import com.example.project_1.data.common.Resource
import com.example.project_1.domain.model.plant.Plant
import com.example.project_1.domain.repository.plant.RemotePlantRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPlantListUseCase @Inject constructor(private val remotePlantRepository: RemotePlantRepository) {
    suspend operator fun invoke(): Flow<Resource<List<Plant>>>{
        return remotePlantRepository.getPlantList()
    }
}