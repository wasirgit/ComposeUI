package com.example.jetpackui.data.repository

import com.example.jetpackui.common.ApiState
import com.example.jetpackui.data.model.Artist
import com.example.jetpackui.data.model.ArtistDetail
import com.example.jetpackui.data.model.MovieDetail
import com.example.jetpackui.data.network.ApiService
import com.example.jetpackui.screens.domain.repository.MovieDetailsRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by Asieuzzaman Wasir on 19,January,2024
 */
class MovieDetailsRepoImpl @Inject constructor(private val apiService: ApiService) :
    MovieDetailsRepo {
    override suspend fun getMovieDetails(movieId: Int): Flow<ApiState<MovieDetail>> = flow {
        emit(ApiState.Loading)
        try {
            val movieDetail = apiService.getMovieDetails(movieId)
            emit(ApiState.Success(movieDetail))
        } catch (e: Exception) {
            emit(ApiState.Failure(e))
        }
    }

    override suspend fun getMovieCredit(movieId: Int): Flow<ApiState<Artist>> = flow {
        emit(ApiState.Loading)
        try {
            val artist = apiService.getMovieCredit(movieId)
            emit(ApiState.Success(artist))
        } catch (e: Exception) {
            emit(ApiState.Failure(e))
        }
    }

    override suspend fun getArtistDetails(personalID: Int): Flow<ApiState<ArtistDetail>> = flow {
        emit(ApiState.Loading)
        try {
            val artistDetails = apiService.getArtistDetails(personalID)
            emit(ApiState.Success(artistDetails))
        } catch (e: Exception) {
            emit(ApiState.Failure(e))
        }
    }
}