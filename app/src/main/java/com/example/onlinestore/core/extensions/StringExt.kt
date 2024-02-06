package com.example.onlinestore.core.extensions

import com.example.onlinestore.core.validator.CyrillicValidator

fun String.isValidOrEmpty(): Boolean {
    return CyrillicValidator(this).validate() || this.isEmpty()
}

fun String.isValidAndNotEmpty(): Boolean {
    return CyrillicValidator(this).validate() && this.isNotEmpty()
}