package com.example.project_1.domain.usecase.database

import com.example.project_1.domain.model.plant.Plant
import com.example.project_1.domain.model.user.User
import com.example.project_1.domain.repository.plant.LocalPlantRepository
import javax.inject.Inject

class DeleteFavouritePlantUseCase @Inject constructor(
    private val localPlantRepository: LocalPlantRepository
) {
    suspend operator fun invoke(user: User, plant: Plant) {
        localPlantRepository.deleteFavouritePlant(user, plant)
    }
}