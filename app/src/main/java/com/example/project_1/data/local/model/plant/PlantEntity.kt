package com.example.project_1.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//@Entity
//data class PlantEntity(
//    @PrimaryKey val id: Int,
//    @ColumnInfo(name = "name")
//    val name: String,
//    @ColumnInfo(name = "species")
//    val species: String,
//    @ColumnInfo(name = "family")
//    val family: String,
//    @ColumnInfo(name = "watering_schedule")
//    val wateringSchedule: String,
//    @ColumnInfo(name = "sunlight_requirement")
//    val sunlightRequirement: String,
//    @ColumnInfo(name = "growth_height")
//    val growthHeight: String,
//    @ColumnInfo(name = "blooming_season")
//    val bloomingSeason: String,
//    @ColumnInfo(name = "description")
//    val description: String,
//    @ColumnInfo(name = "image")
//    val image: String
//)
@Entity
data class PlantEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "species")
    val species: String,
    @ColumnInfo(name = "family")
    val family: String,
    @ColumnInfo(name = "watering_schedule")
    val wateringSchedule: String,
    @ColumnInfo(name = "sunlight_requirement")
    val sunlightRequirement: String,
    @ColumnInfo(name = "growth_height")
    val growthHeight: String,
    @ColumnInfo(name = "blooming_season")
    val bloomingSeason: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "image")
    val image: String,
    @ColumnInfo(name = "user_id")
    val userId: String
)
