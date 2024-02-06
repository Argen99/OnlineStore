package com.example.onlinestore.ui.fragments.main.profile

import androidx.lifecycle.viewModelScope
import com.example.domain.model.UserData
import com.example.domain.use_case.GetUserDataUseCase
import com.example.onlinestore.core.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val getUserDataUseCase: GetUserDataUseCase,
): BaseViewModel() {

    private val _userState = MutableStateFlow<UserData?>(null)
    val userState = _userState.asStateFlow()

    init {
        viewModelScope.launch {
            getUserDataUseCase().collectLatest {
                _userState.value = it
            }
        }
    }
}