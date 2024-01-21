package com.example.project_1.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.project_1.data.local.dao.plant.PlantDao
import com.example.project_1.data.local.model.PlantEntity

@Database(entities = [PlantEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun plantDao(): PlantDao
}
