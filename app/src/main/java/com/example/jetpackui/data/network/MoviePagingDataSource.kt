package com.example.jetpackui.data.network

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.jetpackui.data.model.BaseModel
import com.example.jetpackui.data.model.Movie
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Asieuzzaman Wasir on 18,January,2024
 */
class MoviePagingDataSource @Inject constructor(private val apiService: ApiService) :
    PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
       return try {
            val nextPage = params.key ?: 1
            val movieList = apiService.getNowPlayingMovieList(nextPage)
            LoadResult.Page(
                data = movieList.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (movieList.results.isNotEmpty()) movieList.page + 1 else null
            )
        } catch (exception: Exception) {
            Timber.e(exception)
           return LoadResult.Error(exception)
        }
    }
}