package com.example.project_1.presentation.event.detail

sealed class DetailEvent {
    data class GetPlantDetail(val plantId: Int) : DetailEvent()
}