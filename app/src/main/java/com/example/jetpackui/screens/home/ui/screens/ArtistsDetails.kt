package com.example.jetpackui.screens.home.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.JetPackUI.R
import com.example.jetpackui.data.network.ApiURL
import com.example.jetpackui.screens.home.ui.components.BackButton
import com.example.jetpackui.screens.home.viewmodel.MovieDetailsViewModel

/**
 * Created by Asieuzzaman Wasir on 23,January,2024
 */
@Composable
fun ArtistsDetails(
    navHostController: NavHostController,
    personId: Int,
    viewModel: MovieDetailsViewModel = hiltViewModel(),
) {

    val artistDetails by viewModel.artistDetails.collectAsStateWithLifecycle()
    LaunchedEffect(key1 = true) {
        viewModel.getArtistDetails(personId)
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
                text = stringResource(id = R.string.artists_details),
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.weight(1.0f),
                textAlign = TextAlign.Center
            )
        }
    }) {
        Column(modifier = Modifier.padding(it)) {

            if (artistDetails.isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            if (artistDetails.error.isNotEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = artistDetails.error, color = MaterialTheme.colorScheme.error
                    )
                }
            }
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                Row {
                    Image(
                        painter = rememberAsyncImagePainter(ApiURL.IMAGE_URL.plus(artistDetails.data?.profilePath)),
                        contentDescription = null,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .width(150.dp)
                            .height(220.dp)

                    )
                    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                        artistDetails.data?.let { artist ->
                            Text(text = artist.name, style = MaterialTheme.typography.titleLarge)
                            Text(text = "Know For", style = MaterialTheme.typography.titleMedium)
                            Text(
                                text = artist.knownForDepartment,
                                style = MaterialTheme.typography.bodySmall
                            )
                            Text(text = "BirthDay", style = MaterialTheme.typography.titleMedium)
                            Text(
                                text = artist.birthday,
                                style = MaterialTheme.typography.bodySmall
                            )
                            Text(text = "BirthPlace", style = MaterialTheme.typography.titleMedium)
                            Text(
                                text = artist.placeOfBirth,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }

                    }
                }
                Text(text = "Biography", style = MaterialTheme.typography.titleMedium)
                artistDetails.data?.let { artist ->
                    Text(
                        text = artist.biography,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

            }

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PersonalDetails() {
    Column {
        Text(text = "K H", style = MaterialTheme.typography.titleMedium)
        Text(text = "Know For", style = MaterialTheme.typography.titleMedium)
        Text(text = "Acting", style = MaterialTheme.typography.bodySmall)
    }

}