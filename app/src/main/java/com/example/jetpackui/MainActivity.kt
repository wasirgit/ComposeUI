package com.example.jetpackui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackui.screens.login.LoginScreen
import com.example.jetpackui.screens.login.home.HomeScreen
import com.example.jetpackui.ui.theme.AppTheme

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
        NavHost(navController = navController, startDestination = "welcome") {
            composable("welcome") {
                WelcomeScreen(navController = navController)
            }
            composable("login") {
                LoginScreen(navController = navController)
            }
            composable("homeScreen") {
                HomeScreen(navHostController = navController)
            }
        }
    }
}