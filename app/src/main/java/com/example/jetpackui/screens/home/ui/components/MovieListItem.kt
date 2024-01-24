package com.example.jetpackui.screens.home.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.example.jetpackui.data.model.Movie
import com.example.jetpackui.data.network.ApiURL

/**
 * Created by Asieuzzaman Wasir on 18,January,2024
 */

@Composable
fun MovieItemRow(item: Movie, onClick: (movie: Movie) -> Unit) {
    Card(
        modifier = Modifier
            .padding(horizontal = 18.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable { onClick(item) },
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface)
    ) {
        val painter = rememberAsyncImagePainter(ApiURL.IMAGE_URL + item.backdropPath)
        val transition by animateFloatAsState(
            targetValue = if (painter.state is AsyncImagePainter.State.Success) 1f else 0f,
            label = ""
        )
        Column {
            Box(
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painter,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(8.dp))
                        .alpha(transition)
                )

                Row(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .align(Alignment.TopEnd), verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Filled.Star, contentDescription = null, tint = Color.Yellow
                    )
                    Text(
                        text = item.voteAverage.toString() + "/ 10",
                        color = Color.Yellow,
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.padding(start = 5.dp)
                    )
                }


            }
            Text(
                text = item.title,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 8.dp)
            )
            Spacer(modifier = Modifier.padding(vertical = 4.dp))
            Text(
                text = item.overview,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleSmall,
                maxLines = 2,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 8.dp),
                overflow = TextOverflow.Ellipsis
            )
        }

    }
}

@Composable
@Preview(showBackground = true)
fun PreviewMovieItemRow() {
    MovieItemRow(
        item = Movie(
            false,
            "https://unsplash.com/photos/a-woman-working-on-a-laptop-6uAssP0vuPs",
            emptyList(),
            1,
            "asdf",
            "wetryt",
            "erthgfghfgfhg es hwea wert wh",
            2.00,
            "",
            "",
            "qwertyuio",
            false,
            1.00,
            1
        )
    ) {

    }
}