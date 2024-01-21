package com.example.project_1.presentation.event.favourites

sealed class FavouritesEvent {
    data class GetFavouritesList(val userId: String): FavouritesEvent()
}