package com.example.jetpackui.screens.home.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackui.common.onFailure
import com.example.jetpackui.common.onLoading
import com.example.jetpackui.common.onSuccess
import com.example.jetpackui.data.model.Artist
import com.example.jetpackui.data.model.ArtistDetail
import com.example.jetpackui.data.model.MovieDetail
import com.example.jetpackui.screens.domain.usecase.MovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Asieuzzaman Wasir on 19,January,2024
 */
@HiltViewModel
class MovieDetailsViewModel @Inject constructor(private val movieDetailsUseCase: MovieDetailsUseCase) :
    ViewModel() {
    private val _movieDetails = MutableStateFlow(MovieDetailsState())
    val movieDetails = _movieDetails.asStateFlow()

    private val _artists = MutableStateFlow(MovieArtistsState())
    val artists = _artists.asStateFlow()


    private val _artistDetails = MutableStateFlow(ArtistDetailsState())
    val artistDetails = _artistDetails.asStateFlow()
    fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            movieDetailsUseCase.getMovieDetails(movieId).onSuccess {
                Timber.e("$it")
                _movieDetails.value = MovieDetailsState(data = it)
            }.onFailure {
                Timber.e("${it.message}")
                _movieDetails.value = MovieDetailsState(error = it.message!!)
            }.onLoading {
                Timber.e("Loading")
                _movieDetails.value = MovieDetailsState(isLoading = true)
            }.collect()
        }
    }


    fun getMovieCredit(movieId: Int) {
        viewModelScope.launch {
            movieDetailsUseCase.getMovieCredit(movieId).onSuccess {
                _artists.value = MovieArtistsState(data = it)
            }.onFailure {
                _artists.value = MovieArtistsState(error = it.message!!)
            }.onLoading {
                _artists.value = MovieArtistsState(isLoading = true)
            }.collect()
        }
    }


    fun getArtistDetails(personalId: Int) {
        viewModelScope.launch {
            movieDetailsUseCase.getArtistDetails(personalId).onSuccess {
                _artistDetails.value = ArtistDetailsState(data = it)
            }.onFailure {
                _artistDetails.value = ArtistDetailsState(error = it.message!!)
            }.onLoading {
                _artistDetails.value = ArtistDetailsState(isLoading = true)
            }.collect()
        }
    }
}


data class ArtistDetailsState(
    val data: ArtistDetail? = null,
    val error: String = "",
    val isLoading: Boolean = false,
)

data class MovieArtistsState(
    val data: Artist? = null,
    val error: String = "",
    val isLoading: Boolean = false,
)

data class MovieDetailsState(
    val data: MovieDetail? = null,
    val error: String = "",
    val isLoading: Boolean = false,
)