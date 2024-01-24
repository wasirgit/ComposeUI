package com.example.jetpackui.screens.domain.usecase

import androidx.paging.PagingData
import com.example.jetpackui.common.ApiState
import com.example.jetpackui.common.map
import com.example.jetpackui.data.model.BaseModel
import com.example.jetpackui.data.model.Movie
import com.example.jetpackui.screens.domain.repository.MovieRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by Asieuzzaman Wasir on 15,January,2024
 */
class MovieSearchUseCase @Inject constructor(
    private val movieRepo: MovieRepo,
) {
    suspend fun getNowInPlayMovie(): Flow<PagingData<Movie>> {
        return movieRepo.getNowPlayingMovies()
    }

    suspend fun searchMovie(query: String): Flow<ApiState<List<Movie>>> {
        return movieRepo.searchMovie(query).map {
            it.map { baseMovie ->
                baseMovie.results
            }
        }
    }
}