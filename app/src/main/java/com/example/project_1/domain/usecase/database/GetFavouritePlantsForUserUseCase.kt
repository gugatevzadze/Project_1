package com.example.project_1.domain.usecase.database

import com.example.project_1.domain.model.plant.Plant
import com.example.project_1.domain.model.user.User
import com.example.project_1.domain.repository.plant.LocalPlantRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavouritePlantsForUserUseCase @Inject constructor(
    private val localPlantRepository: LocalPlantRepository
) {
    suspend operator fun invoke(user: User): Flow<List<Plant>> {
        return localPlantRepository.getFavouritePlantsForUser(user)
    }
}