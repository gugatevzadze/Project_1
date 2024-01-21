package com.example.project_1.presentation.event.detail

import com.example.project_1.presentation.model.list.PlantModel

sealed class DetailEvent {
    data class GetPlantDetail(val plantId: Int) : DetailEvent()
}