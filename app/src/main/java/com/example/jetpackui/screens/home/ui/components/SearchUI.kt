package com.example.jetpackui.screens.home.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.jetpackui.data.network.ApiURL
import com.example.jetpackui.screens.home.viewmodel.MovieSearchViewModel

/**
 * Created by Asieuzzaman Wasir on 23,January,2024
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchUI(
    isSearchVisible: MutableState<Boolean>,
    vieModel: MovieSearchViewModel,
    onClickMovie: (Int) -> Unit,
) {
    val movieState by vieModel.moviesSearchState.collectAsStateWithLifecycle()
    val queryString = remember {
        mutableStateOf("")
    }
    androidx.compose.material3.SearchBar(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(MaterialTheme.colorScheme.primary),
        query = queryString.value,
        onQueryChange = { newQueryString ->
            queryString.value = newQueryString
            vieModel.searchMovie(newQueryString)
        },
        onSearch = {

        },
        active = isSearchVisible.value,
        onActiveChange = {
            isSearchVisible.value = it
        },
        trailingIcon = {
            if (queryString.value.trim().isNotEmpty()) {

                IconButton(onClick = {
                    queryString.value = ""
                }) {
                    Icon(
                        imageVector = Icons.Filled.Clear,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            } else {
                IconButton(onClick = {

                }) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        },
        placeholder = {
            Text(
                text = "Search your movie", color = Color.White
            )
        },
        leadingIcon = {
            IconButton(onClick = {
                isSearchVisible.value = false
            }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        },
        colors = SearchBarDefaults.colors(
            containerColor = MaterialTheme.colorScheme.primary,
            dividerColor = Color.LightGray,
            inputFieldColors = TextFieldDefaults.colors(
                unfocusedLabelColor = Color.White,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            )
        )
    ) {
        if (movieState.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
                CircularProgressIndicator()
            }

        } else if (movieState.error.isNotEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
                Text(text = movieState.error, color = MaterialTheme.colorScheme.error)
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier.padding(5.dp),
                content = {
                    items(items = movieState.data, itemContent = {
                        Image(painter = rememberAsyncImagePainter(ApiURL.IMAGE_URL.plus(it.posterPath)),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(180.dp)
                                .padding(5.dp)
                                .clip(MaterialTheme.shapes.medium)
                                .clickable {
                                    onClickMovie(it.id)
                                })
                    })
                })
        }


    }
}