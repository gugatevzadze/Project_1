package com.example.project_1.presentation.event.favourites

import com.example.project_1.presentation.model.list.PlantModel

sealed class FavouritesEvent {
    data class GetFavouritesList(val userId: String): FavouritesEvent()
    data class RemovePlantFromFavourite(val plant: PlantModel): FavouritesEvent()
}