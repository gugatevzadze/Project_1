package com.example.project_1.domain.usecase.plant

import com.example.project_1.data.common.Resource
import com.example.project_1.domain.model.plant.Plant
import com.example.project_1.domain.repository.plant.RemotePlantRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPlantDetailUseCase @Inject constructor(private val remotePlantRepository: RemotePlantRepository) {
    suspend operator fun invoke(plantId: Int): Flow<Resource<Plant>>{
        return remotePlantRepository.getPlantDetails(plantId)
    }
}