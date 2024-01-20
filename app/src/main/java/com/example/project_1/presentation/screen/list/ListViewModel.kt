package com.example.project_1.presentation.screen.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_1.data.common.Resource
import com.example.project_1.domain.usecase.auth.LogoutUseCase
import com.example.project_1.domain.usecase.datastore.ClearSessionDataStoreUseCase
import com.example.project_1.domain.usecase.plant.GetPlantListUseCase
import com.example.project_1.presentation.event.list.ListEvent
import com.example.project_1.presentation.mapper.list.toPresentation
import com.example.project_1.presentation.model.list.PlantModel
import com.example.project_1.presentation.state.list.ListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getPlantListUseCase: GetPlantListUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val clearSessionDataStoreUseCase: ClearSessionDataStoreUseCase
) : ViewModel() {

    private val _listState = MutableStateFlow(ListState())
    val listState: SharedFlow<ListState> get() = _listState

    private val _listNavigationEvent = MutableSharedFlow<ListNavigationEvent>()
    val listNavigationEvent: SharedFlow<ListNavigationEvent> get() = _listNavigationEvent

    fun onEvent(event: ListEvent) {
        when (event) {
            is ListEvent.GetPlantList -> getPlantList()
            is ListEvent.PlantItemClick -> onPlantItemClick(event.plant)
            is ListEvent.PlantSearch -> onPlantSearch(event.query)
        }
    }

    private fun getPlantList() {
        viewModelScope.launch {
            getPlantListUseCase.invoke().collect { it ->
                when (it) {
                    is Resource.Success -> {
                        _listState.update { currentState ->
                            currentState.copy(
                                plants = it.data.map { it.toPresentation() }
                            )
                        }
                    }

                    is Resource.Error -> {
                        _listState.update { currentState ->
                            currentState.copy(
                                errorMessage = it.errorMessage
                            )
                        }
                    }

                    is Resource.Loading -> {
                        _listState.update { currentState ->
                            currentState.copy(
                                isLoading = it.loading
                            )
                        }
                    }
                }
            }
        }
    }

    private fun onPlantItemClick(plant: PlantModel) {
        viewModelScope.launch {
            _listNavigationEvent.emit(ListNavigationEvent.NavigateToDetail(plant.id))
        }
    }

    private fun onPlantSearch(query: String) {
        viewModelScope.launch {
            _listState.update { currentState ->
                val originalList = currentState.originalPlants ?: currentState.plants.orEmpty()
                val filteredList = if (query.isEmpty()) {
                    originalList
                } else {
                    originalList.filter { it.name.contains(query, true) }
                }
                currentState.copy(
                    plants = filteredList,
                    originalPlants = currentState.originalPlants ?: currentState.plants.orEmpty()
                )
            }
        }
    }


    sealed interface ListNavigationEvent {
        data class NavigateToDetail(val plantId: Int) : ListNavigationEvent
    }
}