package com.example.jetpackui.screens.home.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.jetpackui.common.ApiState
import com.example.jetpackui.common.onFailure
import com.example.jetpackui.common.onLoading
import com.example.jetpackui.common.onSuccess
import com.example.jetpackui.data.model.Features
import com.example.jetpackui.data.model.Movie
import com.example.jetpackui.screens.domain.usecase.MovieSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by Asieuzzaman Wasir on 15,January,2024
 */
@HiltViewModel
class MovieSearchViewModel @Inject constructor(
    private val movieSearchUseCase: MovieSearchUseCase,
) : ViewModel() {
    private val _moviesState: MutableStateFlow<PagingData<Movie>> =
        MutableStateFlow(value = PagingData.empty())
    val moviesState: MutableStateFlow<PagingData<Movie>> get() = _moviesState

    private val _moviesSearchState = MutableStateFlow(MovieState())
    val moviesSearchState = _moviesSearchState.asStateFlow()

    fun getMovies() {
        viewModelScope.launch {
            movieSearchUseCase.getNowInPlayMovie().distinctUntilChanged().cachedIn(viewModelScope)
                .collect {
                    _moviesState.value = it
                }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    fun searchMovie(query: String) {
        viewModelScope.launch {
            flowOf(query)
                .debounce(300)
                .filter { it.trim().isEmpty().not() }
                .distinctUntilChanged()
                .flatMapConcat {
                    movieSearchUseCase.searchMovie(query)
                }.onSuccess {
                    _moviesSearchState.value = MovieState(data = it)
                }.onFailure {
                    _moviesSearchState.value = MovieState(error = it.message!!)
                }.onLoading {
                    _moviesSearchState.value = MovieState(isLoading = true)
                }.collect()
        }
    }
}

data class MovieState(
    val data: List<Movie> = emptyList(),
    val error: String = "",
    val isLoading: Boolean = false,
)