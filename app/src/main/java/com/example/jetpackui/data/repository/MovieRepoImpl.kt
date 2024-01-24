package com.example.jetpackui.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.jetpackui.common.ApiState
import com.example.jetpackui.data.model.BaseModel
import com.example.jetpackui.data.model.Movie
import com.example.jetpackui.data.network.ApiService
import com.example.jetpackui.data.network.MoviePagingDataSource
import com.example.jetpackui.screens.domain.repository.MovieRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by Asieuzzaman Wasir on 16,January,2024
 */
class MovieRepoImpl @Inject constructor(private val apiService: ApiService) : MovieRepo {
    override suspend fun getNowPlayingMovies(): Flow<PagingData<Movie>> = Pager(
        pagingSourceFactory = { MoviePagingDataSource(apiService) },
        config = PagingConfig(pageSize = 1)
    ).flow

    override suspend fun searchMovie(query: String): Flow<ApiState<BaseModel>> = flow {
        emit(ApiState.Loading)
        try {
            val searchResult = apiService.searchMovie(query)
            emit(ApiState.Success(searchResult))
        } catch (e: Exception) {
            emit(ApiState.Failure(e))
        }
    }
}