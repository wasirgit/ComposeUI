package com.example.jetpackui.screens.domain.repository

import com.example.jetpackui.common.ApiState
import com.example.jetpackui.data.model.Artist
import com.example.jetpackui.data.model.ArtistDetail
import com.example.jetpackui.data.model.MovieDetail
import kotlinx.coroutines.flow.Flow

/**
 * Created by Asieuzzaman Wasir on 19,January,2024
 */
interface MovieDetailsRepo {
    suspend fun getMovieDetails(movieId: Int): Flow<ApiState<MovieDetail>>
    suspend fun getMovieCredit(movieId: Int): Flow<ApiState<Artist>>
    suspend fun getArtistDetails(personalID: Int): Flow<ApiState<ArtistDetail>>
}