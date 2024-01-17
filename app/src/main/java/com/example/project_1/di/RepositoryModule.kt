package com.example.project_1.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.project_1.data.common.AuthResponseHandler
import com.example.project_1.data.common.ResponseHandler
import com.example.project_1.data.repository.auth.AuthRepositoryImpl
import com.example.project_1.data.repository.datastore.DataStoreRepositoryImpl
import com.example.project_1.data.repository.plant.PlantRepositoryImpl
import com.example.project_1.data.service.plant.PlantApiService
import com.example.project_1.domain.repository.auth.AuthRepository
import com.example.project_1.domain.repository.datastore.DataStoreRepository
import com.example.project_1.domain.repository.plant.PlantRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDataStoreRepository(dataStore: DataStore<Preferences>): DataStoreRepository {
        return DataStoreRepositoryImpl(dataStore = dataStore)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(firebaseAuth: FirebaseAuth, authResponseHandler: AuthResponseHandler): AuthRepository {
        return AuthRepositoryImpl(firebaseAuth = firebaseAuth, authResponseHandler = authResponseHandler)
    }

    @Provides
    @Singleton
    fun providePlantRepository(
        @Named("ListService") plantListService: PlantApiService,
        @Named("DetailService") plantDetailService: PlantApiService,
        responseHandler: ResponseHandler
    ): PlantRepository {
        return PlantRepositoryImpl(
            plantListService = plantListService,
            plantDetailService = plantDetailService,
            responseHandler = responseHandler
        )
    }

}