package com.example.project_1.presentation.event.list

import com.example.project_1.presentation.event.detail.DetailEvent
import com.example.project_1.presentation.model.list.PlantModel

sealed class ListEvent {
    data object GetPlantList : ListEvent()
    data class PlantItemClick(val plant: PlantModel) : ListEvent()
    data class PlantSearch(val query: String) : ListEvent()
    data class AddPlantToFavorite(val plant: PlantModel) : ListEvent()
//    data class RemovePlantFromFavorite(val plant: PlantModel) : ListEvent()
}