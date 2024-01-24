package com.example.jetpackui.data.repository

import com.example.jetpackui.common.ApiState
import com.example.jetpackui.data.model.Features
import com.example.jetpackui.data.network.ApiService
import com.example.jetpackui.screens.domain.repository.FeaturesRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

/**
 * Created by Asieuzzaman Wasir on 12,January,2024
 */
class FeaturesRepoImpl @Inject constructor(private val apiService: ApiService) : FeaturesRepo {
    override suspend fun getFeatures(): Flow<ApiState<Features>> = flow {
        emit(ApiState.Loading)
        delay(2000)

        val results = mutableListOf<Features.Results>()
        results.add(Features.Results(identifier = 1, "Lazy List"))
        results.add(Features.Results(identifier = 2, "Image Loader"))
        results.add(Features.Results(identifier = 3, "Scroll View"))

        val features = Features(results)
        emit(ApiState.Success(features))
    }

}