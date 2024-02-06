package com.example.onlinestore.core.validator

import com.example.onlinestore.core.utils.Object

class CyrillicValidator(private val data: String) : BaseValidator() {
    override fun validate(): Boolean {
        return Regex(Object.CYRILLIC_REGEX).matches(data)
    }
}