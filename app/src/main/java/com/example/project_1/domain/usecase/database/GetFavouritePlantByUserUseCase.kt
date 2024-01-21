package com.example.project_1.domain.usecase.database

import com.example.project_1.data.repository.plant.LocalPlantRepositoryImpl
import com.example.project_1.domain.repository.plant.LocalPlantRepository
import javax.inject.Inject

class GetFavouritePlantByUserUseCase @Inject constructor(
    private val localPlantRepository: LocalPlantRepository
) {
    suspend operator fun invoke(userId:String) = localPlantRepository.getFavouritePlantByUser(userId = userId)
}