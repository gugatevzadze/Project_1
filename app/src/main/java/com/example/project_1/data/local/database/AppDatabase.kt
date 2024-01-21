package com.example.project_1.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.project_1.data.local.dao.plant.PlantDao
import com.example.project_1.data.local.dao.user.UserDao
import com.example.project_1.data.local.model.PlantEntity
import com.example.project_1.data.local.model.UserPlantCrossRef
import com.example.project_1.data.local.model.user.UserEntity

//@Database(entities = [PlantEntity::class], version = 1)
//abstract class AppDatabase : RoomDatabase() {
//    abstract fun plantDao(): PlantDao
//}
@Database(entities = [PlantEntity::class, UserEntity::class, UserPlantCrossRef::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun plantDao(): PlantDao
    abstract fun userDao(): UserDao
}