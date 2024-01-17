package com.example.project_1.domain.repository.plant

import com.example.project_1.data.common.Resource
import com.example.project_1.domain.model.plant.Plant
import kotlinx.coroutines.flow.Flow

interface PlantRepository {
    suspend fun getPlantList(): Flow<Resource<List<Plant>>>
    suspend fun getPlantDetails(id: Int): Flow<Resource<Plant>>
}