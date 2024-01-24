package com.example.jetpackui.screens.domain.usecase

import com.example.jetpackui.common.ApiState
import com.example.jetpackui.data.model.Artist
import com.example.jetpackui.data.model.ArtistDetail
import com.example.jetpackui.data.model.MovieDetail
import com.example.jetpackui.screens.domain.repository.MovieDetailsRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Asieuzzaman Wasir on 19,January,2024
 */
class MovieDetailsUseCase @Inject constructor(private val movieDetailsRepo: MovieDetailsRepo) {
    suspend fun getMovieDetails(movieId: Int): Flow<ApiState<MovieDetail>> {
        return movieDetailsRepo.getMovieDetails(movieId)
    }

    suspend fun getMovieCredit(movieId: Int): Flow<ApiState<Artist>> {
        return movieDetailsRepo.getMovieCredit(movieId)
    }

    suspend fun getArtistDetails(personalId: Int): Flow<ApiState<ArtistDetail>> {
        return movieDetailsRepo.getArtistDetails(personalId)
    }
}