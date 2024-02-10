package com.example.onlinestore.ui.fragments.main.catalog

import androidx.lifecycle.viewModelScope
import com.example.domain.use_case.AddProductToFavoritesUseCase
import com.example.domain.use_case.GetProductsUseCase
import com.example.domain.use_case.RemoveProductFromFavoritesUseCase
import com.example.onlinestore.core.base.BaseViewModel
import com.example.onlinestore.core.ui.UIState
import com.example.onlinestore.core.utils.Object.CATEGORIES
import com.example.onlinestore.ui.model.ProductUI
import com.example.onlinestore.ui.model.toUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CatalogViewModel(
    private val getProductsUseCase: GetProductsUseCase,
    private val addToFavoritesUseCase: AddProductToFavoritesUseCase,
    private val removeFromFavoritesUseCase: RemoveProductFromFavoritesUseCase
) : BaseViewModel() {

    private val _mainData = MutableStateFlow<List<ProductUI>>(emptyList())
    private val _productsState = mutableUiStateFlow<List<ProductUI>>()
    val productsState = _productsState.asStateFlow()

    init {
        getProducts()
    }

    private fun getProducts() {
        viewModelScope.launch {
            getProductsUseCase().gatherRequest(_productsState, _mainData) { data ->
                data.map { it.toUI().copy(images = it.id.getImagesById()) }
            }
        }
    }

    fun onFavoriteClick(product: ProductUI) {
        viewModelScope.launch(Dispatchers.IO) {
            if (product.isFavorite) {
                addToFavoritesUseCase(product.toDomain())
            } else {
                removeFromFavoritesUseCase(product.toDomain())
            }
        }
    }

    fun filterByCategory(text: String) {
        CATEGORIES.forEach { category ->
            if (text == category.name) {
                _productsState.update {
                    UIState.Success(
                        if (category.tag.isEmpty()) {
                            _mainData.value
                        } else {
                            _mainData.value.filter {
                                it.tags.contains(category.tag)
                            }
                        }
                    )
                }
            }
        }
    }

    fun sortProducts(text: String) {
        val data = (_productsState.value as? UIState.Success)?.data
        data?.let {
            _productsState.update {
                when (text) {
                    "По популярности" -> {
                        UIState.Success(
                            data.sortedBy {
                                it.feedback?.rating
                            }.reversed()
                        )
                    }

                    "По уменьшению цены" -> {
                        UIState.Success(
                            data.sortedBy {
                                it.price.priceWithDiscount.toInt()
                            }.reversed()
                        )
                    }

                    "По возрастанию цены" -> {
                        UIState.Success(
                            data.sortedBy {
                                it.price.priceWithDiscount.toInt()
                            }
                        )
                    }

                    else -> {
                        UIState.Success(data)
                    }
                }
            }
        }
    }
}