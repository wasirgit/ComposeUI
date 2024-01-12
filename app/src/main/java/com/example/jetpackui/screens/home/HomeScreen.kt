package com.example.jetpackui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.JetPackUI.R
/**
 * Created by Asieuzzaman Wasir on 12,January,2024
 */
@Composable
fun HomeScreen(navHostController: NavHostController) {
    Surface(modifier = Modifier.fillMaxSize()) {
        val context = LocalContext.current
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = R.drawable.bg),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )
        }

    }
}

@Composable
@Preview
fun PreviewHomeScreen() {
    HomeScreen(navHostController = rememberNavController())
}