package com.example.project_1.data.local.mapper.plant

import com.example.project_1.data.local.model.PlantEntity
import com.example.project_1.domain.model.plant.Plant

fun PlantEntity.toDomain(): Plant {
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

fun Plant.toData(userId:String): PlantEntity {
    return PlantEntity(
        id = id,
        name = name,
        species = species,
        family = family,
        wateringSchedule = wateringSchedule,
        sunlightRequirement = sunlightRequirement,
        growthHeight = growthHeight,
        bloomingSeason = bloomingSeason,
        description = description,
        image = image,
        userId = userId
    )
}