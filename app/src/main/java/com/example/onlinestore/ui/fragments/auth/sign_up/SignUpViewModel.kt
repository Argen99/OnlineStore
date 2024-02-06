package com.example.onlinestore.ui.fragments.auth.sign_up

import androidx.lifecycle.viewModelScope
import com.example.domain.model.UserData
import com.example.domain.repository.UserRepository
import com.example.onlinestore.core.base.BaseViewModel
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val repository: UserRepository
) : BaseViewModel() {

    fun saveData(data: UserData) {
        viewModelScope.launch {
            repository.saveUserData(data)
        }
    }
}