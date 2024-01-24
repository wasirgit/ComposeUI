package com.example.jetpackui.screens.home.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.JetPackUI.R
import com.example.jetpackui.screens.home.ui.components.BackButton
import com.example.jetpackui.data.model.Features
import com.example.jetpackui.screens.home.viewmodel.HomeViewModel

/**
 * Created by Asieuzzaman Wasir on 12,January,2024
 */
@Composable
fun HomeScreen(
    navHostController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    Image(
        painter = painterResource(id = R.drawable.bg),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )
    Column(modifier = Modifier.padding(start = 10.dp, end = 10.dp)) {
        val result = viewModel.result.value

        if (result.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Color.White)
            }
        }
        if (result.error.isNotBlank()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = result.error)
            }
        }
        if (result.data.isNotEmpty()) {
            LazyColumn {
                items(result.data, key = {
                    it.identifier
                }) { it ->
                    ItemRow(it) {
                        if (it.identifier == 1) {
                            navHostController.navigate("movieSearch")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ItemRow(data: Features.Results, onclickListener: (data: Features.Results) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onclickListener(data) },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(4.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterVertically)
                    .weight(1f) // This makes the Column flexible
            ) {
                Text(
                    text = data.featureName,
                    style = TextStyle(fontSize = 16.sp),
                    textAlign = TextAlign.Center
                )
            }
            Icon(
                Icons.Filled.KeyboardArrowRight,
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 16.dp)
                    .align(alignment = Alignment.CenterVertically)
            )
        }
    }

}

@Composable
@Preview
fun ItemRowPreview() {
    BackButton {

    }
    Column {
        ItemRow(data = Features.Results(identifier = 0, "First feature name")) {

        }
    }
}

