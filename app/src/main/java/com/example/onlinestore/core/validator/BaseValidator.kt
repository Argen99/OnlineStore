package com.example.onlinestore.core.validator

abstract class BaseValidator : IValidator {
    companion object {
        fun validate(vararg validators: IValidator): Boolean {
            validators.forEach {
                val result = it.validate()
                if (!result)
                    return false
            }
            return true
        }
    }
}