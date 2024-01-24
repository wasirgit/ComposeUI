package com.example.jetpackui.screens.home.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.JetPackUI.R
import com.example.jetpackui.data.model.Cast
import com.example.jetpackui.data.model.MovieDetail
import com.example.jetpackui.data.network.ApiURL
import com.example.jetpackui.screens.home.ui.components.BackButton
import com.example.jetpackui.screens.home.viewmodel.MovieDetailsViewModel

/**
 * Created by Asieuzzaman Wasir on 19,January,2024
 */
@Composable
fun MovieDetailsScreen(
    navHostController: NavHostController,
    movieId: Int,
    viewModel: MovieDetailsViewModel = hiltViewModel(),
    onClickItem: (Int) -> Unit,
) {
    val movieDetails by viewModel.movieDetails.collectAsStateWithLifecycle()
    val artists by viewModel.artists.collectAsStateWithLifecycle()
    LaunchedEffect(key1 = true) {
        viewModel.getMovieDetails(movieId)
        viewModel.getMovieCredit(movieId)
    }


    Scaffold(topBar = {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(MaterialTheme.colorScheme.primary),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BackButton {
                navHostController.popBackStack()
            }
            Text(
                text = stringResource(id = R.string.movie_details),
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.weight(1.0f),
                textAlign = TextAlign.Center
            )
        }
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            if (movieDetails.isLoading) {
                Box(
                    contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator()
                }
            }
            if (movieDetails.error.isNotEmpty()) {
                Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Text(text = movieDetails.error, color = MaterialTheme.colorScheme.error)
                }
            }

            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                Image(
                    painter = rememberAsyncImagePainter(ApiURL.IMAGE_URL.plus(movieDetails.data?.backdrop_path)),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)

                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(start = 15.dp, end = 15.dp)
                ) {
                    if (movieDetails.data != null) {
                        Descriptions(movieDetails.data!!)
                    }
                    artists.data?.let { it ->
                        BindArtists(onClickItem, it.cast)
                    }
                }

            }


        }
    }
}

@Composable
fun BindArtists(onClickItem: (Int) -> Unit, casts: List<Cast>) {
    Column(modifier = Modifier.padding(bottom = 10.dp)) {
        Text(
            text = "Casts",
            color = Color.Black,
            modifier = Modifier.padding(10.dp),
            maxLines = 2,
            fontWeight = FontWeight.Bold,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleLarge
        )
        LazyRow {
            items(casts, itemContent = { item ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = rememberAsyncImagePainter(ApiURL.IMAGE_URL.plus(item.profilePath)),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(240.dp)
                            .width(180.dp)
                            .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp)
                            .clip(shape = MaterialTheme.shapes.large)
                            .clickable {
                                onClickItem.invoke(item.id)
                            }
                    )
                    Text(
                        text = item.name,
                        modifier = Modifier.padding(end = 10.dp, top = 5.dp, bottom = 5.dp)
                    )
                }
            })

        }
    }
}


@Composable
fun Descriptions(movieDetails: MovieDetail) {
    Text(
        text = movieDetails.title,
        color = Color.Black,
        modifier = Modifier.padding(10.dp),
        maxLines = 2,
        fontWeight = FontWeight.Bold,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.titleLarge
    )
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp, bottom = 10.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Language", fontWeight = FontWeight.Bold
            )
            Text(
                text = movieDetails.original_language,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )
        }
        Column(
            modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Rating", fontWeight = FontWeight.Bold)
            Text(
                text = movieDetails.vote_average.toString(),
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )
        }
        Column(
            modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Release Date", fontWeight = FontWeight.Bold)
            Text(
                text = movieDetails.release_date,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )
        }
    }
    Text(
        text = "Descriptions",
        color = Color.Black,
        modifier = Modifier.padding(10.dp),
        maxLines = 2,
        fontWeight = FontWeight.Bold,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.titleLarge
    )
    Text(
        text = movieDetails.overview,
        modifier = Modifier.padding(start = 10.dp, end = 10.dp),
        style = MaterialTheme.typography.bodySmall,
    )

}
