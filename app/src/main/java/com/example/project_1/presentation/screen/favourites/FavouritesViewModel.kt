package com.example.project_1.presentation.screen.favourites

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_1.domain.usecase.database.GetFavouritePlantByUserUseCase
import com.example.project_1.presentation.event.favourites.FavouritesEvent
import com.example.project_1.presentation.mapper.list.toPresentation
import com.example.project_1.presentation.model.list.PlantModel
import com.example.project_1.presentation.state.favourites.FavouritesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val getFavouritePlantByUserUseCase: GetFavouritePlantByUserUseCase
): ViewModel() {
    private val _favouritePlant = MutableStateFlow(FavouritesState())
    val favouritePlant: StateFlow<FavouritesState> get() = _favouritePlant


    fun onEvent(event: FavouritesEvent) {
        when (event) {
            is FavouritesEvent.GetFavouritesList -> getFavouritePlantByUser(event.userId)
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
}