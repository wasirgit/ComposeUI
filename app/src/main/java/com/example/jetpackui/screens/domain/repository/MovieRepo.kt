package com.example.jetpackui.screens.domain.repository

import androidx.paging.PagingData
import com.example.jetpackui.common.ApiState
import com.example.jetpackui.data.model.BaseModel
import com.example.jetpackui.data.model.Features
import com.example.jetpackui.data.model.Movie
import kotlinx.coroutines.flow.Flow

/**
 * Created by Asieuzzaman Wasir on 16,January,2024
 */
interface MovieRepo {
    suspend fun getNowPlayingMovies(): Flow<PagingData<Movie>>
    suspend fun searchMovie(query: String): Flow<ApiState<BaseModel>>
}