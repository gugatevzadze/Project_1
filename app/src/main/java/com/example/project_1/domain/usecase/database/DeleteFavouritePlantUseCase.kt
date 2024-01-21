package com.example.project_1.domain.usecase.database

import android.util.Log
import com.example.project_1.domain.model.plant.Plant
import com.example.project_1.domain.repository.plant.LocalPlantRepository
import javax.inject.Inject

class DeleteFavouritePlantUseCase @Inject constructor(
    private val localPlantRepository: LocalPlantRepository
){
    suspend operator fun invoke(userId:String, plant: Plant){
        Log.d("DeleteFavPlantUseCase", "Deleting plant: ${plant.id} for user: $userId")
        localPlantRepository.deleteFavouritePlant(userId = userId,plant = plant)
    }
}