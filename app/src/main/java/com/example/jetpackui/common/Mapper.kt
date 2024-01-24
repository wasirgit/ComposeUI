package com.example.jetpackui.common

/**
 * Created by Asieuzzaman Wasir on 12,January,2024
 */

interface Mapper<F, T> {
    fun fromMap(from: F): T
}