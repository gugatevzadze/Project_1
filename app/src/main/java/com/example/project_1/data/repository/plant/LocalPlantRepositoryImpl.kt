package com.example.project_1.data.repository.plant

import com.example.project_1.data.local.dao.plant.PlantDao
import com.example.project_1.data.local.dao.user.UserDao
import com.example.project_1.data.local.mapper.plant.toData
import com.example.project_1.data.local.mapper.plant.toDomain
import com.example.project_1.data.local.mapper.user.toData
import com.example.project_1.data.local.mapper.userplantcorssref.toData
import com.example.project_1.data.local.model.PlantEntity
import com.example.project_1.data.local.model.user.UserEntity
import com.example.project_1.domain.model.plant.Plant
import com.example.project_1.domain.model.user.User
import com.example.project_1.domain.model.userplancrossref.UserPlantCrossRef
import com.example.project_1.domain.repository.plant.LocalPlantRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalPlantRepositoryImpl @Inject constructor(
    private val plantDao: PlantDao,
    private val userDao: UserDao
) : LocalPlantRepository {

//    override suspend fun getFavouritePlantByUser(userId:String): Flow<List<Plant>> {
//        return plantDao.getFavouritePlantByUser(userId = userId).map { plantList ->
//            plantList.map { it.toDomain() }
//        }
//    }
//
//    override suspend fun insertFavouritePlant(userId: String, plant: Plant) {
//        Log.d("LocalPlantRepoImpl", "Inserting plant: ${plant.id} into database for user: $userId")
//        return plantDao.insertFavouritePlant(plant.toData())
//    }
//
//    override suspend fun deleteFavouritePlant(userId: String, plant: Plant) {
//        Log.d("LocalPlantRepoImpl", "deleting: ${plant.id} from database for user: $userId")
//        return plantDao.deleteFavouritePlant(plant.toData())
//    }

    ///userdao
    override suspend fun insertUser(user: User) {
        userDao.insertUser(user.toData())
    }

    override suspend fun insertPlant(plant: Plant) {
        plantDao.insertPlant(plant.toData())
    }

    override suspend fun insertFavouritePlant(user: User, plant: Plant) {
        userDao.insertUser(user.toData())

        plantDao.insertPlant(plant.toData())

        userDao.insertUserPlantCrossRef(UserPlantCrossRef(user.id, plant.id).toData())
    }

    override suspend fun deleteFavouritePlant(user: User, plant: Plant) {
        val crossRef = UserPlantCrossRef(user.id, plant.id)
        userDao.deleteUserPlantCrossRef(crossRef.toData())
    }

    override suspend fun getFavouritePlantsForUser(user: User): Flow<List<Plant>> {
        return userDao.getFavoritePlantsForUser(user.id).map { plantList ->
            plantList.map { it.toDomain() }
        }
    }
}