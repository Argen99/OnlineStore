package com.example.domain.use_case

import com.example.domain.repository.ProductRepository

class GetProductsUseCase(
    private val repository: ProductRepository
) {
    suspend operator fun invoke() = repository.getProducts()
}