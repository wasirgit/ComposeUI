package com.example.jetpackui.data.model

/**
 * Created by Asieuzzaman Wasir on 12,January,2024
 */
data class Features(val results: List<Results>) {
    data class Results(
        val identifier: Int,
        val featureName: String
    )
}