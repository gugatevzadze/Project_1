package com.example.project_1.data.local.dao.user

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.project_1.data.local.model.PlantEntity
import com.example.project_1.data.local.model.userplancrossref.UserPlantCrossRefEntity
import com.example.project_1.data.local.model.user.UserEntity
import com.example.project_1.domain.model.userplancrossref.UserPlantCrossRef
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserPlantCrossRef(crossRef: UserPlantCrossRefEntity)

    @Delete
    suspend fun deleteUserPlantCrossRef(crossRef: UserPlantCrossRefEntity)

    @Query("SELECT * FROM plantentity INNER JOIN userplantcrossrefentity ON plantentity.id = userplantcrossrefentity.plantId WHERE userplantcrossrefentity.userId = :userId")
    fun getFavoritePlantsForUser(userId: String): Flow<List<PlantEntity>>
}