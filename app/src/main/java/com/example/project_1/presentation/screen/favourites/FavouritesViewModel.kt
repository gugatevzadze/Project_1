package com.example.project_1.presentation.screen.favourites

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_1.domain.usecase.database.DeleteFavouritePlantUseCase
import com.example.project_1.domain.usecase.database.GetFavouritePlantByUserUseCase
import com.example.project_1.presentation.event.favourites.FavouritesEvent
import com.example.project_1.presentation.mapper.list.toDomain
import com.example.project_1.presentation.mapper.list.toPresentation
import com.example.project_1.presentation.model.list.PlantModel
import com.example.project_1.presentation.state.favourites.FavouritesState
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val getFavouritePlantByUserUseCase: GetFavouritePlantByUserUseCase,
    private val deleteFavouritePlantUseCase: DeleteFavouritePlantUseCase
): ViewModel() {
    private val _favouritePlant = MutableStateFlow(FavouritesState())
    val favouritePlant: StateFlow<FavouritesState> get() = _favouritePlant


    fun onEvent(event: FavouritesEvent) {
        when (event) {
            is FavouritesEvent.GetFavouritesList -> getFavouritePlantByUser(event.userId)
            is FavouritesEvent.RemovePlantFromFavourite -> onRemovePlantFromFavorite(event.plant)
        }
    }
    private fun getFavouritePlantByUser(userId: String) {
        viewModelScope.launch {
            getFavouritePlantByUserUseCase.invoke(userId = userId).collect { it ->
                Log.d("FavouritesViewModel", "Fetched plants: ${it.size} for user: $userId")
                _favouritePlant.update { currentState ->
                    currentState.copy(
                        favourites = it.map { it.toPresentation() }
                    )
                }
            }
        }
    }
    private fun onRemovePlantFromFavorite(plant: PlantModel) {
        viewModelScope.launch {
            val user = Firebase.auth.currentUser
            val userId = user?.uid
            if (userId != null) {
                deleteFavouritePlantUseCase.invoke(userId, plant.toDomain())
                Log.d("ListViewModel", "Removed plant from favorites: ${plant.id}")
                _favouritePlant.update { currentState ->
                    currentState.copy(
                        favourites = currentState.favourites?.map {
                            if (it.id == plant.id) it.copy(isFavorite = false) else it
                        }
                    )
                }
            } else {
                Log.d("ListViewModel", "Failed to remove plant from favorites: User not logged in")
            }
        }
    }
}