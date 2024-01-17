package com.example.project_1.data.repository.plant

import com.example.project_1.data.common.Resource
import com.example.project_1.data.common.ResponseHandler
import com.example.project_1.data.mapper.base.mapToDomain
import com.example.project_1.data.mapper.plant.toDomain
import com.example.project_1.data.service.plant.PlantApiService
import com.example.project_1.domain.model.plant.Plant
import com.example.project_1.domain.repository.plant.PlantRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Named

class PlantRepositoryImpl(
    @Named("ListService") private val plantListService: PlantApiService,
    @Named("DetailService") private val plantDetailService: PlantApiService,
    private val responseHandler: ResponseHandler
) : PlantRepository {
    override suspend fun getPlantList(): Flow<Resource<List<Plant>>> {
        return responseHandler.safeApiCall {
            plantListService.getPlantList()
        }.mapToDomain { plantListDto ->
            plantListDto.map { it.toDomain() }
        }
    }

    override suspend fun getPlantDetails(id: Int): Flow<Resource<Plant>> {
        return responseHandler.safeApiCall {
            plantDetailService.getPlantDetails(id)
        }.mapToDomain { plantDetailDto ->
            plantDetailDto.toDomain()
        }
    }
}