package com.example.project_1.presentation.state.favourites

import com.example.project_1.presentation.model.list.PlantModel

data class FavouritesState(
    val favourites: List<PlantModel>? = null,
    val originalFavourites: List<PlantModel>? = null
)
