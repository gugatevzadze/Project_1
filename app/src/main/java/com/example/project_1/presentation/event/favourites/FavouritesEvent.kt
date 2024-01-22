package com.example.project_1.presentation.event.favourites

import com.example.project_1.presentation.model.list.PlantModel
import com.example.project_1.presentation.model.user.UserModel

sealed class FavouritesEvent {
    data class GetFavouritesList(val user: UserModel): FavouritesEvent()
    data class RemovePlantFromFavourite(val plant: PlantModel): FavouritesEvent()

    data class FavouritePlantSearch(val query: String) : FavouritesEvent()
}