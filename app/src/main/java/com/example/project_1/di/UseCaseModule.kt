package com.example.project_1.di

import com.example.project_1.domain.repository.auth.AuthRepository
import com.example.project_1.domain.repository.datastore.DataStoreRepository
import com.example.project_1.domain.repository.plant.PlantRepository
import com.example.project_1.domain.usecase.auth.LoginUseCase
import com.example.project_1.domain.usecase.auth.LogoutUseCase
import com.example.project_1.domain.usecase.auth.RegisterUseCase
import com.example.project_1.domain.usecase.datastore.ClearSessionDataStoreUseCase
import com.example.project_1.domain.usecase.datastore.ReadSessionDataStoreUseCase
import com.example.project_1.domain.usecase.datastore.SaveSessionDataStoreUseCase
import com.example.project_1.domain.usecase.plant.GetPlantDetailUseCase
import com.example.project_1.domain.usecase.plant.GetPlantListUseCase
import com.example.project_1.domain.usecase.validator.EmailValidatorUseCase
import com.example.project_1.domain.usecase.validator.FieldsValidatorUseCase
import com.example.project_1.domain.usecase.validator.PasswordMatchingUseCase
import com.example.project_1.domain.usecase.validator.PasswordValidatorUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideLoginUseCase(
        authRepository: AuthRepository
    ): LoginUseCase {
        return LoginUseCase(
            authRepository = authRepository
        )
    }

    @Provides
    @Singleton
    fun provideRegisterUseCase(
        authRepository: AuthRepository
    ): RegisterUseCase {
        return RegisterUseCase(
            authRepository = authRepository
        )
    }

    @Provides
    @Singleton
    fun provideLogoutUseCase(
        authRepository: AuthRepository
    ): LogoutUseCase {
        return LogoutUseCase(
            authRepository = authRepository
        )
    }

    @Provides
    @Singleton
    fun provideClearSessionDataStoreUseCase(
        dataStoreRepository: DataStoreRepository
    ): ClearSessionDataStoreUseCase {
        return ClearSessionDataStoreUseCase(
            dataStoreRepository = dataStoreRepository
        )
    }

    @Provides
    @Singleton
    fun provideReadSessionDataStoreUseCase(
        dataStoreRepository: DataStoreRepository
    ): ReadSessionDataStoreUseCase {
        return ReadSessionDataStoreUseCase(
            dataStoreRepository = dataStoreRepository
        )
    }

    @Provides
    @Singleton
    fun provideSaveSessionDataStoreUseCase(
        dataStoreRepository: DataStoreRepository
    ): SaveSessionDataStoreUseCase {
        return SaveSessionDataStoreUseCase(
            dataStoreRepository = dataStoreRepository
        )
    }

    @Provides
    @Singleton
    fun provideEmailValidatorUseCase(
    ): EmailValidatorUseCase {
        return EmailValidatorUseCase()
    }

    @Provides
    @Singleton
    fun provideFieldsValidatorUseCase(
    ):FieldsValidatorUseCase{
        return FieldsValidatorUseCase()
    }

    @Provides
    @Singleton
    fun providePasswordValidatorUseCase(
    ): PasswordValidatorUseCase {
        return PasswordValidatorUseCase()
    }

    @Provides
    @Singleton
    fun providePasswordMatchingUseCase(
    ): PasswordMatchingUseCase {
        return PasswordMatchingUseCase()
    }

    @Provides
    @Singleton
    fun providePlantListUseCase(
        plantRepository: PlantRepository
    ): GetPlantListUseCase {
        return GetPlantListUseCase(
            plantRepository = plantRepository
        )
    }

    @Provides
    @Singleton
    fun providePlantDetailUseCase(
        plantRepository: PlantRepository
    ): GetPlantDetailUseCase {
        return GetPlantDetailUseCase(
            plantRepository = plantRepository
        )
    }

}