package com.example.jetpackui.screens.domain.mapper

import com.example.jetpackui.common.Mapper
import com.example.jetpackui.data.model.BaseModel
import com.example.jetpackui.data.model.Features
import com.example.jetpackui.data.model.Movie
import javax.inject.Inject

/**
 * Created by Asieuzzaman Wasir on 12,January,2024
 */
class FeatureMapper @Inject constructor() : Mapper<Features, List<Features.Results>> {
    override fun fromMap(from: Features): List<Features.Results> {
        return from.results.map {
            Features.Results(
                identifier = it.identifier,
                featureName = it.featureName
            )
        }
    }
}
