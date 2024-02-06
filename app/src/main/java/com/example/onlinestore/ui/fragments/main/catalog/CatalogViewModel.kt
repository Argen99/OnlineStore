package com.example.onlinestore.ui.fragments.main.catalog

import androidx.lifecycle.viewModelScope
import com.example.domain.model.ProductModel
import com.example.domain.use_case.AddToFavoritesUseCase
import com.example.domain.use_case.GetProductsUseCase
import com.example.domain.use_case.RemoveFromFavoritesUseCase
import com.example.onlinestore.core.base.BaseViewModel
import com.example.onlinestore.core.ui.UIState
import com.example.onlinestore.core.utils.Object.CATEGORIES
import com.example.onlinestore.core.utils.Object.IMAGES_MAP
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CatalogViewModel(
    private val getProductsUseCase: GetProductsUseCase,
    private val addToFavoritesUseCase: AddToFavoritesUseCase,
    private val removeFromFavoritesUseCase: RemoveFromFavoritesUseCase
) : BaseViewModel() {

    private val _mainData = MutableStateFlow<List<ProductModel>>(emptyList())
    private val _productsState = mutableUiStateFlow<List<ProductModel>>()
    private val _currentProduct = MutableStateFlow<ProductModel?>(null)
    val currentProduct = _currentProduct.asStateFlow()
    val productsState = _productsState.asStateFlow()

    init {
        viewModelScope.launch {
            getProductsUseCase().gatherRequest(_productsState, _mainData)
        }
    }

    fun onItemClick(id: String) {
        var images = emptyList<Int>()
        IMAGES_MAP.forEach {
            if (id == it.key)
                images = it.value
        }
        _currentProduct.update {
            _mainData.value.find { model ->
                id == model.id
            }?.copy(
                images = images
            )
        }
    }

    fun onFavoriteClick(product: ProductModel) {
        viewModelScope.launch(Dispatchers.IO) {
            if (product.isFavorite) {
                removeFromFavoritesUseCase(product)
            } else {
                addToFavoritesUseCase(product)
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