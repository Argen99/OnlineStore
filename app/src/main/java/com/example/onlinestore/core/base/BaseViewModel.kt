package com.example.onlinestore.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.core.Either
import com.example.onlinestore.R
import com.example.onlinestore.core.ui.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    protected fun <T> mutableUiStateFlow() = MutableStateFlow<UIState<T>>(UIState.Idle())

    protected fun <T, S> Flow<Either<T>>.gatherRequest(
        state: MutableStateFlow<UIState<S>>,
        data: MutableStateFlow<S>,
        mappedData: (data: T) -> S
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            state.value = UIState.Loading()
            this@gatherRequest.collect {
                when (it) {
                    is Either.Error -> state.value =
                        UIState.Error(it.exception.localizedMessage ?: "Unknown error")
                    is Either.Success -> {
                        data.value = mappedData(it.data)
                        state.value = UIState.Success(mappedData(it.data))
                    }
                }
            }
        }
    }

    protected fun String.getImagesById(): List<Int> {
        return when (this) {
            "cbf0c984-7c6c-4ada-82da-e29dc698bb50" -> listOf(
                R.drawable.product_image_6,
                R.drawable.product_image_5
            )

            "54a876a5-2205-48ba-9498-cfecff4baa6e" -> listOf(
                R.drawable.product_image_1,
                R.drawable.product_image_2
            )

            "75c84407-52e1-4cce-a73a-ff2d3ac031b3" -> listOf(
                R.drawable.product_image_5,
                R.drawable.product_image_6
            )

            "16f88865-ae74-4b7c-9d85-b68334bb97db" -> listOf(
                R.drawable.product_image_3,
                R.drawable.product_image_4
            )

            "26f88856-ae74-4b7c-9d85-b68334bb97db" -> listOf(
                R.drawable.product_image_2,
                R.drawable.product_image_3
            )

            "15f88865-ae74-4b7c-9d81-b78334bb97db" -> listOf(
                R.drawable.product_image_6,
                R.drawable.product_image_1
            )

            "88f88865-ae74-4b7c-9d81-b78334bb97db" -> listOf(
                R.drawable.product_image_4,
                R.drawable.product_image_3
            )

            "55f58865-ae74-4b7c-9d81-b78334bb97db" -> listOf(
                R.drawable.product_image_1,
                R.drawable.product_image_5
            )

            else -> emptyList()
        }
    }
}