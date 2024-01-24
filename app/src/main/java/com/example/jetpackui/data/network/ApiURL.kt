package com.example.jetpackui.data.network

/**
 * Created by Asieuzzaman Wasir on 16,January,2024
 */
object ApiURL {
    private const val API_KEY = "d69f38ad138a0e1afa946e8493636a05"
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val IMAGE_URL = "https://image.tmdb.org/t/p/w342"
    const val MOVIE_LIST = "movie/now_playing?api_key=$API_KEY&language=en-US"
    const val MOVIE_DETAIL ="movie/{movieId}?api_key=$API_KEY&language=en-US"
    const val MOVIE_CREDIT ="movie/{movieId}/credits?api_key=$API_KEY&language=en-US"
    const val ARTIST_DETAIL ="person/{personId}?api_key=$API_KEY&language=en-US"
    const val SEARCH_MOVIE ="search/movie?api_key=$API_KEY&language=en-US&page=1&include_adult=false"
}