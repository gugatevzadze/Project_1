package com.example.project_1.presentation.screen.favourites

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_1.domain.model.user.User
import com.example.project_1.domain.usecase.database.DeleteFavouritePlantUseCase
import com.example.project_1.domain.usecase.database.GetFavouritePlantsForUserUseCase
import com.example.project_1.presentation.event.favourites.FavouritesEvent
import com.example.project_1.presentation.mapper.list.toDomain
import com.example.project_1.presentation.mapper.list.toPresentation
import com.example.project_1.presentation.mapper.user.toDomain
import com.example.project_1.presentation.model.list.PlantModel
import com.example.project_1.presentation.model.user.UserModel
import com.example.project_1.presentation.state.favourites.FavouritesState
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val getFavouritePlantsForUserUseCase: GetFavouritePlantsForUserUseCase,
    private val deleteFavouritePlantUseCase: DeleteFavouritePlantUseCase
) : ViewModel() {
    private val _favouritePlant = MutableStateFlow(FavouritesState())
    val favouritePlant: StateFlow<FavouritesState> get() = _favouritePlant


    fun onEvent(event: FavouritesEvent) {
        when (event) {
            is FavouritesEvent.GetFavouritesList -> getFavouritePlants(event.user)
            is FavouritesEvent.RemovePlantFromFavourite -> onRemovePlantFromFavorite(event.plant)
        }
    }
    private fun getFavouritePlants(user: UserModel) {
        viewModelScope.launch {
            val firebaseUser = Firebase.auth.currentUser
            val userId = firebaseUser?.uid
            if (userId != null) {
                getFavouritePlantsForUserUseCase.invoke(UserModel(userId).toDomain()).collect { plants ->
                    _favouritePlant.value =
                        FavouritesState(favourites = plants.map { it.toPresentation() })
                }
            } else {
                Log.d("FavouritesViewModel", "Failed to get favourite plants: User not logged in")
            }
        }
    }

    private fun onRemovePlantFromFavorite(plant: PlantModel) {
        viewModelScope.launch {
            val firebaseUser = Firebase.auth.currentUser
            val userId = firebaseUser?.uid
            if (userId != null) {
                deleteFavouritePlantUseCase.invoke(UserModel(userId).toDomain(), plant.toDomain())
                getFavouritePlants(UserModel(userId))
            } else {
                Log.d("FavouritesViewModel", "Failed to remove plant from favorites: User not logged in")
            }
        }
    }
}