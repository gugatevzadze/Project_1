package com.example.project_1.presentation.model.list
data class PlantModel(
    val id: Int,
    val name: String,
    val species: String,
    val family: String,
    val wateringSchedule: String,
    val sunlightRequirement: String,
    val growthHeight: String,
    val bloomingSeason: String,
    val description: String,
    val image: String,
    var isFavorite: Boolean = false
)
