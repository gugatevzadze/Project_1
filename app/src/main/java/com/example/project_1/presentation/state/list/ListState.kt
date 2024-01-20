package com.example.project_1.presentation.state.list

import com.example.project_1.presentation.model.list.PlantModel

data class ListState(
    val plants: List<PlantModel>? = null,
    val originalPlants: List<PlantModel>? = null,
    val errorMessage: String? = null,
    val isLoading: Boolean = false
)