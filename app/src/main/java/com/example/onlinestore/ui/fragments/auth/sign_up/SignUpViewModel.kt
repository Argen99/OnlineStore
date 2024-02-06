package com.example.onlinestore.ui.fragments.auth.sign_up

import com.example.domain.model.UserData
import com.example.domain.use_case.SaveUserDataUseCase
import com.example.onlinestore.core.base.BaseViewModel

class SignUpViewModel(
    private val saveUserDataUseCase: SaveUserDataUseCase
) : BaseViewModel() {

    fun saveData(data: UserData) {
        saveUserDataUseCase(data)
    }
}