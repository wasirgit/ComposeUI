package com.example.jetpackui.screens.login


import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.JetPackUI.R
import com.example.jetpackui.components.FilledButton
import com.example.jetpackui.components.PasswordField
import com.example.jetpackui.components.UserInputField
import com.example.jetpackui.ui.theme.gilroyFontBold
import com.example.jetpackui.ui.theme.gilroyFontNormal
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    navController: NavHostController,
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val context = LocalContext.current
        Box(modifier = Modifier.fillMaxSize()) {
            /// Background Image
            Image(
                painter = painterResource(id = R.drawable.bg),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.FillBounds
            )

        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.jetpack_compose),
                contentDescription = null,
                modifier = Modifier.size(120.dp),
                contentScale = ContentScale.Fit
            )

            Text(
                text = "SignIn",
                style = TextStyle(
                    fontFamily = gilroyFontBold,
                    color = Color.White,
                    fontSize = 28.sp
                ),
                modifier = Modifier.align(Alignment.Start)
            )
            Spacer(modifier = Modifier.padding(top = 10.dp))
            Text(
                text = stringResource(R.string.sign_in_to_explore_other_features),
                color = Color.White,
                fontFamily = gilroyFontNormal,
                modifier = Modifier.align(
                    Alignment.Start
                )
            )
            Spacer(modifier = Modifier.padding(top = 10.dp))
            var inputValue by rememberSaveable { mutableStateOf("") }
            var inputPass by rememberSaveable { mutableStateOf("") }
            var loading by remember { mutableStateOf(false) }
            val scope = rememberCoroutineScope() // Create a coroutine scope
            var isError by rememberSaveable { mutableStateOf(false) }
            UserInputField(value = inputValue, isError = isError, onChange = {
                inputValue = it
            })

            Spacer(modifier = Modifier.padding(top = 5.dp))
            PasswordField(value = inputPass, onChange = {
                inputPass = it
            }, submit = {
                Toast.makeText(
                    context,
                    inputPass,
                    Toast.LENGTH_LONG
                ).show()
            })
            Spacer(modifier = Modifier.padding(top = 16.dp))
            if (!loading) {
                FilledButton("Login") {
                    if (!isValidEmail(inputValue)) {
                        isError = true
                        return@FilledButton
                    }
                    loading = true
                    scope.launch {
                        loadProgress {
                            navController.navigate("homeScreen")
                        }
                    }
                }
            }

            if (loading) {
                CircularProgressIndicator(
                    modifier = Modifier.width(64.dp),
                    color = MaterialTheme.colors.secondary,
                )
            }

        }
    }
}

fun isValidEmail(email: String): Boolean {
    val emailRegex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()
    return email.matches(emailRegex)
}

suspend fun loadProgress(updateProgress: () -> Unit) {
    delay(2000)
    updateProgress()
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(navController = rememberNavController())
}