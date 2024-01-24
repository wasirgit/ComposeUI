package com.example.jetpackui.screens.domain.usecase

import com.example.jetpackui.common.ApiState
import com.example.jetpackui.common.map
import com.example.jetpackui.data.model.Features
import com.example.jetpackui.screens.domain.mapper.FeatureMapper
import com.example.jetpackui.screens.domain.repository.FeaturesRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

import javax.inject.Inject

/**
 * Created by Asieuzzaman Wasir on 12,January,2024
 */
class FeaturesUseCase @Inject constructor(
    private val featuresRepo: FeaturesRepo,
    private val mapper: FeatureMapper
) {
    suspend fun getFeatures(): Flow<ApiState<List<Features.Results>>> {
        return featuresRepo.getFeatures().map { result ->
            result.map {
                mapper.fromMap(it)
            }
        }
    }
}