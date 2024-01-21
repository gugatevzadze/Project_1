package com.example.project_1.domain.repository.plant

import com.example.project_1.data.local.model.userplancrossref.UserPlantCrossRefEntity
import com.example.project_1.domain.model.plant.Plant
import com.example.project_1.domain.model.user.User
import com.example.project_1.domain.model.userplancrossref.UserPlantCrossRef
import kotlinx.coroutines.flow.Flow

interface LocalPlantRepository {

//    suspend fun getFavouritePlantByUser(userId:String): Flow<List<Plant>>
//
//    suspend fun insertFavouritePlant(userId: String,plant: Plant)
//
//    suspend fun deleteFavouritePlant(userId: String,plant: Plant)

    ///userdao
    suspend fun insertUser(user: User)
    suspend fun insertPlant(plant: Plant)
    suspend fun insertFavouritePlant(user: User, plant: Plant)
    suspend fun deleteFavouritePlant(user: User, plant: Plant)
    suspend fun getFavouritePlantsForUser(user: User): Flow<List<Plant>>
}