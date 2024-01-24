package com.example.jetpackui.data.network

import com.example.jetpackui.data.model.Artist
import com.example.jetpackui.data.model.ArtistDetail
import com.example.jetpackui.data.model.BaseModel
import com.example.jetpackui.data.model.MovieDetail
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Asieuzzaman Wasir on 15,January,2024
 */
interface ApiService {
    @GET(ApiURL.MOVIE_LIST)
    suspend fun getNowPlayingMovieList(@Query("page") page: Int): BaseModel

    @GET(ApiURL.MOVIE_DETAIL)
    suspend fun getMovieDetails(@Path("movieId") movieId: Int): MovieDetail

    @GET(ApiURL.MOVIE_CREDIT)
    suspend fun getMovieCredit(@Path("movieId") movieId: Int): Artist


    @GET(ApiURL.ARTIST_DETAIL)
    suspend fun getArtistDetails(@Path("personId") personId: Int): ArtistDetail


    @GET(ApiURL.SEARCH_MOVIE)
    suspend fun searchMovie(@Query("query") query: String): BaseModel


}