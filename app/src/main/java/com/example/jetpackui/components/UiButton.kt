package com.example.jetpackui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackui.ui.theme.AppTheme

@Composable
fun FilledButton(buttonText: String, onClick: () -> Unit) {
    Button(
        onClick = { onClick() }, modifier = Modifier
            .width(220.dp)
            .height(55.dp)
    ) {
        Text(
            text = buttonText,
            color = Color.White
        )
    }
}

@Composable
fun OutlinedButtonExample(buttonText: String, onClick: () -> Unit) {
    OutlinedButton(onClick = { onClick() }) {
        Text(text = buttonText)
    }
}


@Composable
fun TextButtonExample(buttonText: String, onClick: () -> Unit) {
    TextButton(onClick = { onClick() }) {
        Text(text = buttonText)
    }
}

@Composable
fun IconButtonExample(onClick: () -> Unit) {
    IconButton(onClick = { onClick() }) {
        Icon(Icons.Filled.Favorite, contentDescription = "Favorite")
    }
}

@Composable
fun IconButtonExample(
    buttonText: String,
    resourceId: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    IconButton(onClick = { onClick() }) {
        Icon(painterResource(id = resourceId), contentDescription = "Favorite")
    }
}

@Preview
@Composable
fun FilledButtonPreview() {
    AppTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FilledButton("Test") {}
            OutlinedButtonExample("Outlined") {}
            TextButtonExample(buttonText = "Text Button") {}
            IconButtonExample() {}
            IconButtonExample() {}
        }
    }
}