package com.example.onlinestore.core.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.core.Either
import com.example.onlinestore.core.ui.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    protected fun <T> mutableUiStateFlow() = MutableStateFlow<UIState<T>>(UIState.Idle())

    protected fun <T> Flow<Either<T>>.gatherRequest(
        state: MutableStateFlow<UIState<T>>,
        data: MutableStateFlow<T>,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            state.value = UIState.Loading()
            this@gatherRequest.collect {
                when (it) {
                    is Either.Error -> state.value =
                        UIState.Error(it.exception.localizedMessage ?: "Unknown error")
                    is Either.Success -> {
                        data.value = it.data
                        state.value = UIState.Success(it.data)
                    }
                }
            }
        }
    }
}