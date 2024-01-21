package com.example.project_1.domain.usecase.database

import android.util.Log
import com.example.project_1.data.repository.plant.LocalPlantRepositoryImpl
import com.example.project_1.domain.model.plant.Plant
import com.example.project_1.domain.repository.plant.LocalPlantRepository
import javax.inject.Inject

class InsertFavouritePlantUseCase @Inject constructor(
    private val localPlantRepository: LocalPlantRepository
) {
    suspend operator fun invoke(userId:String,plant: Plant) {
        Log.d("InsertFavPlantUseCase", "Inserting plant: ${plant.id} for user: $userId")
        localPlantRepository.insertFavouritePlant(userId = userId, plant = plant)
    }
}