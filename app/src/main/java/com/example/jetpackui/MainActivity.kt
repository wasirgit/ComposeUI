package com.example.jetpackui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jetpackui.screens.home.ui.screens.ArtistsDetails
import com.example.jetpackui.screens.login.LoginScreen
import com.example.jetpackui.screens.home.ui.screens.HomeScreen
import com.example.jetpackui.screens.home.ui.screens.MovieSearchScreen
import com.example.jetpackui.screens.home.ui.screens.MovieDetailsScreen
import com.example.jetpackui.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Asieuzzaman Wasir on 12,January,2024
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                NavigationView()
            }
        }
    }

    @Composable
    private fun NavigationView() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "movieSearch") {
            composable("welcome") {
                WelcomeScreen(navController = navController)
            }
            composable("login") {
                LoginScreen(navController = navController)
            }
            composable("homeScreen") {
                HomeScreen(navHostController = navController)
            }
            composable("movieSearch") {
                MovieSearchScreen(navHostController = navController) {
                    navController.navigate(route = "detailsScreen/$it")
                }
            }
            composable(
                route = "detailsScreen/{movieId}",
                arguments = listOf(navArgument("movieId") {
                    type = NavType.IntType
                })
            ) {
                val movieId: Int = it.arguments?.getInt("movieId") ?: -1
                MovieDetailsScreen(navHostController = navController, movieId = movieId) { it ->
                    navController.navigate(route = "artistDetails/$it")
                }
            }

            composable(
                route = "artistDetails/{personal_id}",
                arguments = listOf(navArgument("personal_id") {
                    type = NavType.IntType
                })
            ) {
                val personalId: Int = it.arguments?.getInt("personal_id") ?: -1
                ArtistsDetails(navHostController = navController, personId = personalId)
            }
        }
    }
}