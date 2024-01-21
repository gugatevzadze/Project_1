package com.example.project_1.data.repository.plant

import android.util.Log
import com.example.project_1.data.local.dao.plant.PlantDao
import com.example.project_1.data.local.mapper.plant.toData
import com.example.project_1.data.local.mapper.plant.toDomain
import com.example.project_1.domain.model.plant.Plant
import com.example.project_1.domain.repository.plant.LocalPlantRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalPlantRepositoryImpl @Inject constructor(
    private val plantDao: PlantDao
) : LocalPlantRepository {

    override suspend fun getFavouritePlantByUser(userId:String): Flow<List<Plant>> {
        return plantDao.getFavouritePlantByUser(userId = userId).map { plantList ->
            plantList.map { it.toDomain() }
        }
    }

    override suspend fun insertFavouritePlant(userId: String, plant: Plant) {
        Log.d("LocalPlantRepoImpl", "Inserting plant: ${plant.id} into database for user: $userId")
        return plantDao.insertFavouritePlant(plant.toData(userId))
    }

    override suspend fun deleteFavouritePlant(userId: String, plant: Plant) {
        Log.d("LocalPlantRepoImpl", "Inserting plant: ${plant.id} into database for user: $userId")
        return plantDao.deleteFavouritePlant(plant.toData(userId))
    }
}