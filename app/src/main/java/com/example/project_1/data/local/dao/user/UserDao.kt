//package com.example.project_1.data.local.dao.user
//
//import androidx.room.Dao
//import androidx.room.Insert
//import androidx.room.OnConflictStrategy
//import androidx.room.Query
//import com.example.project_1.data.local.model.PlantEntity
//import com.example.project_1.data.local.model.UserPlantCrossRef
//import com.example.project_1.data.local.model.user.UserEntity
//import kotlinx.coroutines.flow.Flow
//
//@Dao
//interface UserDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertUser(user: UserEntity)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertUserPlantCrossRef(crossRef: UserPlantCrossRef)
//
//    @Query("SELECT * FROM PlantEntity INNER JOIN UserPlantCrossRef ON PlantEntity.id=UserPlantCrossRef.plantId WHERE UserPlantCrossRef.userId=:userId")
//    fun getFavoritePlantsForUser(userId: Int): Flow<List<PlantEntity>>
//}