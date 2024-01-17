package com.example.project_1.data.mapper.plant

import com.example.project_1.data.model.plant.PlantDto
import com.example.project_1.domain.model.plant.Plant

fun PlantDto.toDomain(): Plant {
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