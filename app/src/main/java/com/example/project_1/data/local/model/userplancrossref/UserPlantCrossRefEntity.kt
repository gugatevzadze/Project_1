package com.example.project_1.data.local.model.userplancrossref

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["userId", "plantId"])
data class UserPlantCrossRefEntity(
    val userId: String,
    val plantId: Int
)
