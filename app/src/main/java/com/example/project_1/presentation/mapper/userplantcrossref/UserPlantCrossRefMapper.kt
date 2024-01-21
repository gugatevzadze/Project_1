package com.example.project_1.presentation.mapper.userplantcrossref

import com.example.project_1.domain.model.userplancrossref.UserPlantCrossRef
import com.example.project_1.presentation.model.userplantcrossref.UserPlantCrossRefModel

fun UserPlantCrossRefModel.toDomain(): UserPlantCrossRef {
    return UserPlantCrossRef(
        userId = userId,
        plantId = plantId
    )
}

fun UserPlantCrossRef.toPresentation(): UserPlantCrossRefModel {
    return UserPlantCrossRefModel(
        userId = userId,
        plantId = plantId
    )
}