package com.example.onlinestore.core.extensions

import kotlin.math.abs

fun Int.getNoun(one: String, two: String, five: String): String {
    var n = abs(this)
    n %= 100
    if (n in 5..20) {
        return five
    }
    n %= 10;
    if (n == 1) {
        return one
    }
    if (n in 2..4) {
        return two
    }
    return five
}