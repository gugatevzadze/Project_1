package com.example.project_1.data.local.dao.plant

import android.util.Log
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.project_1.data.local.model.PlantEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PlantDao {

    @Query("SELECT * FROM plantentity WHERE user_id = :userId")
    fun getFavouritePlantByUser(userId:String): Flow<List<PlantEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavouritePlant(plant: PlantEntity)

    @Delete
    suspend fun deleteFavouritePlant(plant: PlantEntity)
}