package com.example.project_1.presentation.state.detail

import com.example.project_1.presentation.model.list.PlantModel

data class DetailState (
    val details: PlantModel? = null,
    val errorMessage: String? = null,
    val isLoading: Boolean = false
)