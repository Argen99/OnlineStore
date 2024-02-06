package com.example.data.remote

import com.example.data.model.ProductResponseDto
import retrofit2.http.GET

interface ProductApiService {

    @GET("v3/97e721a7-0a66-4cae-b445-83cc0bcf9010")
    suspend fun getProducts() : ProductResponseDto
}