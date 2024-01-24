package com.example.jetpackui.screens.domain.repository

import com.example.jetpackui.common.ApiState
import com.example.jetpackui.data.model.Features
import kotlinx.coroutines.flow.Flow

/**
 * Created by Asieuzzaman Wasir on 12,January,2024
 */
interface FeaturesRepo {
    suspend fun getFeatures(): Flow<ApiState<Features>>
}