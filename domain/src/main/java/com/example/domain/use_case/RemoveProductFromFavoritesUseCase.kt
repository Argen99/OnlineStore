package com.example.domain.use_case

import com.example.domain.model.ProductModel
import com.example.domain.repository.ProductRepository

class RemoveProductFromFavoritesUseCase(
    private val repository: ProductRepository
) {
    suspend operator fun invoke(product: ProductModel) = repository.removeFromFavorites(product)
}