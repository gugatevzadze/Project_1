package com.example.project_1.di

import android.content.Context
import androidx.room.Room
import com.example.project_1.data.local.dao.plant.PlantDao
import com.example.project_1.data.local.dao.user.UserDao
import com.example.project_1.data.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context):AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "plant-database"
        ).build()
    }

    @Provides
    @Singleton
    fun providePlantDao(appDatabase: AppDatabase): PlantDao {
        return appDatabase.plantDao()
    }

    ////
//    @Provides
//    @Singleton
//    fun provideUserDao(appDatabase: AppDatabase): UserDao {
//        return appDatabase.userDao()
//    }
}