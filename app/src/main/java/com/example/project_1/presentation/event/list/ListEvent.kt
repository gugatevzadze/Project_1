package com.example.project_1.presentation.event.list

import com.example.project_1.presentation.event.detail.DetailEvent
import com.example.project_1.presentation.model.list.PlantModel
import com.example.project_1.presentation.model.user.UserModel

sealed class ListEvent {
    data object GetPlantList : ListEvent()
    data class PlantItemClick(val plant: PlantModel) : ListEvent()
    data class PlantSearch(val query: String) : ListEvent()
    data class AddPlantToFavorite(val plant: PlantModel, val user:UserModel) : ListEvent()
    data class AddPlantToDatabase(val plant: PlantModel) : ListEvent()
    data class AddUserToDatabase(val user: UserModel) : ListEvent()
}