package com.example.onlinestore.ui.fragments.auth.sign_up

import com.example.domain.use_case.GetUserDataUseCase
import com.example.domain.use_case.SaveUserDataUseCase
import com.example.onlinestore.core.base.BaseViewModel
import com.example.onlinestore.ui.model.UserDataUI
import com.example.onlinestore.ui.model.toUI

class SignUpViewModel(
    private val saveUserDataUseCase: SaveUserDataUseCase,
    private val getUserDataUseCase: GetUserDataUseCase
) : BaseViewModel() {

    private fun saveData(data: UserDataUI) {
        saveUserDataUseCase(data.toDomain())
    }

    fun isAuthorized(data: UserDataUI): Boolean {
        val localData = getUserDataUseCase()?.toUI()
        val result = data == localData
        if (!result) {
            saveData(data)
        }
        return result
    }
}