package com.example.data.remote

import com.example.data.core.makeNetworkRequest
import com.example.data.local.room.ProductsDao
import com.example.data.model.toDto
import com.example.domain.core.Either
import com.example.domain.model.ProductModel
import com.example.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.lang.Exception

class ProductRepositoryImpl(
    private val apiService: ProductApiService,
    private val productsDao: ProductsDao
) : ProductRepository {

    override suspend fun getProducts(): Flow<Either<List<ProductModel>>> = makeNetworkRequest {
        apiService.getProducts().items.map { it.toDomain() }
    }

    override suspend fun getFavorites(): Flow<List<ProductModel>> {
        return productsDao.getProducts().map { data -> data.map { it.toDomain() } }
    }

    override suspend fun addToFavorites(product: ProductModel) {
        productsDao.addProduct(product.toDto())
    }

    override suspend fun removeFromFavorites(product: ProductModel) {
        productsDao.deleteProduct(product.toDto())
    }

    override suspend fun getFavoritesCount(): Int {
        return try {
            productsDao.getCount()
        } catch (e: Exception) {
            e.printStackTrace()
            0
        }
    }
}