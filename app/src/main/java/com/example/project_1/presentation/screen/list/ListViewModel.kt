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
            is ListEvent.Logout -> logout()
            is ListEvent.GetPlantList -> getPlantList()
            is ListEvent.PlantItemClick -> onPlantItemClick(event.plant)
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

    private fun logout() {
        viewModelScope.launch {
            logoutUseCase()
            clearSessionDataStoreUseCase()
            _listNavigationEvent.emit(ListNavigationEvent.NavigateToWelcome)
        }
    }

    sealed interface ListNavigationEvent {
        data object NavigateToWelcome : ListNavigationEvent
        data class NavigateToDetail(val plantId: Int) : ListNavigationEvent
    }
}