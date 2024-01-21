package com.example.project_1.data.local.mapper.userplantcorssref

import com.example.project_1.data.local.model.userplancrossref.UserPlantCrossRefEntity
import com.example.project_1.domain.model.userplancrossref.UserPlantCrossRef

fun UserPlantCrossRefEntity.toDomain(): UserPlantCrossRef {
    return UserPlantCrossRef(
        userId = userId,
        plantId = plantId
    )
}

fun UserPlantCrossRef.toData(): UserPlantCrossRefEntity {
    return UserPlantCrossRefEntity(
        userId = userId,
        plantId = plantId
    )
}