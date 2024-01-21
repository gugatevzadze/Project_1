package com.example.project_1.presentation.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project_1.data.common.Resource
import com.example.project_1.domain.usecase.database.DeleteFavouritePlantUseCase
import com.example.project_1.domain.usecase.database.InsertFavouritePlantUseCase
import com.example.project_1.domain.usecase.plant.GetPlantDetailUseCase
import com.example.project_1.presentation.event.detail.DetailEvent
import com.example.project_1.presentation.mapper.list.toDomain
import com.example.project_1.presentation.mapper.list.toPresentation
import com.example.project_1.presentation.model.list.PlantModel
import com.example.project_1.presentation.state.detail.DetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getPlantDetailUseCase: GetPlantDetailUseCase,
) : ViewModel() {

    private val _detailState = MutableStateFlow(DetailState())

    val detailState: SharedFlow<DetailState> get() = _detailState

    fun onEvent(event: DetailEvent) {
        when (event) {
            is DetailEvent.GetPlantDetail -> getUserDetail(event.plantId)
        }
    }

    private fun getUserDetail(userId: Int) {
        viewModelScope.launch {
            getPlantDetailUseCase.invoke(userId).collect {
                when (it) {
                    is Resource.Success -> {
                        _detailState.update { currentState ->
                            currentState.copy(
                                details = it.data.toPresentation()
                            )
                        }
                    }

                    is Resource.Error -> {
                        _detailState.update { currentState ->
                            currentState.copy(
                                errorMessage = it.errorMessage
                            )
                        }
                    }

                    is Resource.Loading -> {
                        _detailState.update { currentState ->
                            currentState.copy(
                                isLoading = it.loading
                            )
                        }
                    }
                }
            }
        }
    }
}