package com.example.project_1.presentation.mapper.list

import com.example.project_1.domain.model.plant.Plant
import com.example.project_1.presentation.model.list.PlantModel

fun Plant.toPresentation(): PlantModel {
    return PlantModel(
        id = id,
        name = name,
        species = species,
        family = family,
        wateringSchedule = wateringSchedule,
        sunlightRequirement = sunlightRequirement,
        growthHeight = growthHeight,
        bloomingSeason = bloomingSeason,
        description = description,
        image = image
    )
}
fun PlantModel.toDomain() : Plant {
    return Plant(
        id = id,
        name = name,
        species = species,
        family = family,
        wateringSchedule = wateringSchedule,
        sunlightRequirement = sunlightRequirement,
        growthHeight = growthHeight,
        bloomingSeason = bloomingSeason,
        description = description,
        image = image
    )
}
