package com.example.project_1.domain.repository.plant

import com.example.project_1.domain.model.plant.Plant
import kotlinx.coroutines.flow.Flow

interface LocalPlantRepository {

    suspend fun getFavouritePlantByUser(userId:String): Flow<List<Plant>>

    suspend fun insertFavouritePlant(userId: String,plant: Plant)

    suspend fun deleteFavouritePlant(userId: String,plant: Plant)

    ///userdao

}