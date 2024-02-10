package com.example.onlinestore.ui.fragments.main.profile

import androidx.lifecycle.viewModelScope
import com.example.domain.use_case.ClearFavoriteProductsUseCase
import com.example.domain.use_case.ClearUserDataUseCase
import com.example.domain.use_case.GetFavoritesCountUseCase
import com.example.domain.use_case.GetUserDataUseCase
import com.example.onlinestore.core.base.BaseViewModel
import com.example.onlinestore.ui.model.UserDataUI
import com.example.onlinestore.ui.model.toUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val getUserDataUseCase: GetUserDataUseCase,
    private val getFavoritesCountUseCase: GetFavoritesCountUseCase,
    private val clearUserDataUseCase: ClearUserDataUseCase,
    private val clearFavoriteProductsUseCase: ClearFavoriteProductsUseCase
) : BaseViewModel() {

    private val _userState = MutableStateFlow<UserDataUI?>(null)
    val userState = _userState.asStateFlow()

    private val _favoritesCount = MutableStateFlow(0)
    val favoritesCount = _favoritesCount.asStateFlow()

    init {
        getFavoritesCount()
        getUserData()
    }

    private fun getUserData() {
        viewModelScope.launch(Dispatchers.IO) {
            _userState.value = getUserDataUseCase()?.toUI()
        }
    }

    private fun getFavoritesCount() {
        viewModelScope.launch(Dispatchers.IO) {
            getFavoritesCountUseCase().collectLatest {
                _favoritesCount.value = it
            }
        }
    }

    fun exit() {
        viewModelScope.launch {
            clearUserDataUseCase()
            clearFavoriteProductsUseCase()
        }
    }
}