package com.example.onlinestore.ui.fragments.main.profile.favorites.favorite_products

import androidx.lifecycle.viewModelScope
import com.example.domain.model.ProductModel
import com.example.domain.use_case.GetFavoriteProductsUseCase
import com.example.domain.use_case.RemoveProductFromFavoritesUseCase
import com.example.onlinestore.core.base.BaseViewModel
import com.example.onlinestore.ui.model.ProductUI
import com.example.onlinestore.ui.model.toUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavoriteProductsViewModel(
    private val getFavoritesUseCase: GetFavoriteProductsUseCase,
    private val removeFromFavoritesUseCase: RemoveProductFromFavoritesUseCase
): BaseViewModel() {

    private val _favoritesState = MutableStateFlow<List<ProductUI>>(emptyList())
    val favoritesState = _favoritesState.asStateFlow()

    init {
        getFavoriteProducts()
    }

    private fun getFavoriteProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            getFavoritesUseCase().collect { data->
                _favoritesState.value = data.map { it.toUI().copy(images = it.id.getImagesById()) }
            }
        }
    }

    fun removeProductFromFavorites(product: ProductUI) {
        viewModelScope.launch(Dispatchers.IO) {
            removeFromFavoritesUseCase(product.toDomain())
        }
    }
}