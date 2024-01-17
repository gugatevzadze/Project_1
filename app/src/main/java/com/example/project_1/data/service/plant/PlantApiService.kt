package com.example.project_1.data.service.plant

import com.example.project_1.data.model.plant.PlantDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PlantApiService {
    @GET("/api/v1/plants")
    suspend fun getPlantList(): Response<List<PlantDto>>

    @GET("/{id}")
    suspend fun getPlantDetails(@Path("id") id: Int): Response<PlantDto>
}