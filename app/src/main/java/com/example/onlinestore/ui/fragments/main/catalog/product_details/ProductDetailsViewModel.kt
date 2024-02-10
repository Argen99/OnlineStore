package com.example.onlinestore.ui.fragments.main.catalog.product_details

import androidx.lifecycle.viewModelScope
import com.example.domain.use_case.AddProductToFavoritesUseCase
import com.example.domain.use_case.RemoveProductFromFavoritesUseCase
import com.example.onlinestore.core.base.BaseViewModel
import com.example.onlinestore.ui.model.ProductUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductDetailsViewModel(
    private val addProductToFavoritesUseCase: AddProductToFavoritesUseCase,
    private val removeProductFromFavoritesUseCase: RemoveProductFromFavoritesUseCase
) : BaseViewModel() {

    fun onFavoriteClick(product: ProductUI) {
        if (product.isFavorite) {
            removeProductFromFavorites(product)
        } else {
            addProductToFavorites(product)
        }
    }

    fun addProductToFavorites(product: ProductUI) {
        viewModelScope.launch(Dispatchers.IO) {
            addProductToFavoritesUseCase(product.toDomain())
        }
    }

    fun removeProductFromFavorites(product: ProductUI) {
        viewModelScope.launch(Dispatchers.IO) {
            removeProductFromFavoritesUseCase(product.toDomain())
        }
    }
}