package com.example.jetpackui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.JetPackUI.R
import com.example.jetpackui.components.FilledButton
import com.example.jetpackui.ui.theme.gilroyFontBold
import com.example.jetpackui.ui.theme.gilroyFontNormal

/**
 * Created by Asieuzzaman Wasir on 12,January,2024
 */
@Composable
fun WelcomeScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = R.drawable.bg),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp)
        ) {
            Spacer(modifier = Modifier.padding(top = 20.dp))
            Image(
                painter = painterResource(id = R.drawable.jetpack_compose),
                contentDescription = null,
                modifier = Modifier
                    .width(120.dp)
                    .height(120.dp),
                contentScale = ContentScale.Fit
            )


            Spacer(modifier = Modifier.padding(top = 20.dp))

            Text(
                text = "Welcome",
                fontSize = 32.sp,
                fontFamily = gilroyFontBold,
                fontWeight = FontWeight(700),
                color = Color.White
            )
            Spacer(modifier = Modifier.padding(top = 20.dp))
            FilledButton(buttonText = "Sign in with Email") {
                navController.navigate("login")
            }

            Column(modifier = Modifier.padding(top = 12.dp, bottom = 25.dp)) {
                Spacer(modifier = Modifier.weight(1f))
                Row {
                    Text(
                        "Create new account?", style = TextStyle(
                            fontSize = 18.sp,
                            fontFamily = gilroyFontNormal,
                            color = Color.White
                        )
                    )
                    Spacer(modifier = Modifier.padding(start = 10.dp))
                    Text(
                        text = "Sign up", style = TextStyle(
                            fontSize = 18.sp,
                            fontFamily = gilroyFontBold,
                            color = Color.White
                        ),
                        modifier = Modifier.clickable {

                        }
                    )
                }
            }
        }


    }

}


@Preview
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen(rememberNavController())
}