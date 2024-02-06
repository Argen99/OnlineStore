package com.example.domain.repository

import com.example.domain.core.Either
import com.example.domain.model.ProductModel
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun getProducts() : Flow<Either<List<ProductModel>>>
    suspend fun getFavorites() : Flow<List<ProductModel>>
    suspend fun addToFavorites(product: ProductModel)
    suspend fun removeFromFavorites(product: ProductModel)
    suspend fun getFavoritesCount(): Int
}