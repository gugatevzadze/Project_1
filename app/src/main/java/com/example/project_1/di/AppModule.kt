package com.example.project_1.di

import com.example.project_1.BuildConfig
import com.example.project_1.data.common.AuthResponseHandler
import com.example.project_1.data.common.ResponseHandler
import com.example.project_1.data.remote.service.plant.PlantApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideResponseHandler(): ResponseHandler {
        return ResponseHandler()
    }

    @Provides
    @Singleton
    fun provideAuthResponseHandler(): AuthResponseHandler {
        return AuthResponseHandler()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().setLevel(
                    HttpLoggingInterceptor.Level.BODY
                )
            )
            .build()
    }

    @Singleton
    @Provides
    @Named("ListRetrofit")
    fun provideListRetrofitClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.LIST_BASE_URL)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            ).client(provideOkHttpClient()).build()
    }

    @Singleton
    @Provides
    @Named("DetailRetrofit")
    fun provideDetailRetrofitClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.DETAIL_BASE_URL)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder()
                        .add(KotlinJsonAdapterFactory())
                        .build()
                )
            )
            .client(provideOkHttpClient()).build()
    }

    @Singleton
    @Provides
    @Named("ListService")
    fun provideUserListService(
        @Named("ListRetrofit") listRetrofit: Retrofit
    ): PlantApiService {
        return listRetrofit.create(PlantApiService::class.java)
    }

    @Singleton
    @Provides
    @Named("DetailService")
    fun provideUserDetailService(
        @Named("DetailRetrofit") detailRetrofit: Retrofit
    ): PlantApiService {
        return detailRetrofit.create(PlantApiService::class.java)
    }
}