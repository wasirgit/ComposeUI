package com.example.jetpackui.screens.home.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.JetPackUI.R
import com.example.jetpackui.data.model.Movie
import com.example.jetpackui.screens.home.ui.components.MovieItemRow
import com.example.jetpackui.screens.home.ui.components.SearchUI
import com.example.jetpackui.screens.home.viewmodel.MovieSearchViewModel
import com.example.jetpackui.utils.ErrorMessage
import com.example.jetpackui.utils.LoadingNextPageItem
import com.example.jetpackui.utils.PageLoader

/**
 * Created by Asieuzzaman Wasir on 15,January,2024
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieSearchScreen(
    navHostController: NavHostController,
    viewModel: MovieSearchViewModel = hiltViewModel(),
    onClickMovie: (Int) -> Unit,
) {
    val isSearchBarVisible = remember {
        mutableStateOf(false)
    }
    val searchText = remember {
        mutableStateOf("")
    }
    val moviesItem: LazyPagingItems<Movie> = viewModel.moviesState.collectAsLazyPagingItems()
    LaunchedEffect(key1 = true) {
        viewModel.getMovies()
    }

    Scaffold(topBar = {
        if (isSearchBarVisible.value.not()) {
            CenterAlignedTopAppBar(title = {
                Text(
                    text = stringResource(id = R.string.movie_list),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }, colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = MaterialTheme.colorScheme.onPrimary
            ), actions = {
                IconButton(onClick = {
                    isSearchBarVisible.value = true
                }) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            })
        } else {
            SearchUI(
                isSearchVisible = isSearchBarVisible,
                viewModel,
                onClickMovie
            )
        }
    }) {

        Image(
            painter = painterResource(id = R.drawable.bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        LazyColumn(modifier = Modifier.padding(it)) {
            items(moviesItem.itemCount) { index ->
                MovieItemRow(item = moviesItem[index]!!) { movie ->
                    onClickMovie(movie.id)
                }
            }
            moviesItem.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item { PageLoader(modifier = Modifier.fillParentMaxSize()) }
                    }

                    loadState.refresh is LoadState.Error -> {
                        val error = moviesItem.loadState.refresh as LoadState.Error
                        item {
                            ErrorMessage(modifier = Modifier.fillParentMaxSize(),
                                message = error.error.localizedMessage!!,
                                onClickRetry = { retry() })
                        }
                    }

                    loadState.append is LoadState.Loading -> {
                        item { LoadingNextPageItem(modifier = Modifier) }
                    }

                    loadState.append is LoadState.Error -> {
                        val error = moviesItem.loadState.append as LoadState.Error
                        item {
                            ErrorMessage(modifier = Modifier,
                                message = error.error.localizedMessage!!,
                                onClickRetry = { retry() })
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun PreviewMovieSearchScreen() {
    MovieSearchScreen(navHostController = rememberNavController()) {

    }
}