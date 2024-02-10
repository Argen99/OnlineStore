package com.example.domain.use_case

import com.example.domain.repository.ProductRepository

class GetFavoriteProductsUseCase(
    private val repository: ProductRepository
) {
    suspend operator fun invoke() = repository.getFavoriteProducts()
}