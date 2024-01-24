package com.example.jetpackui.screens.home.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color

/**
 * Created by Asieuzzaman Wasir on 23,January,2024
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(isSearchVisible: MutableState<Boolean>, searchText: MutableState<String>) {
    val focusRequiter = FocusRequester()

    TextField(
        value = searchText.value,
        onValueChange = { searchText.value = it },
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequiter),
        singleLine = true,
        trailingIcon = {
            if (searchText.value.trim().isNotEmpty()) {

                IconButton(onClick = {
                    searchText.value = ""
                }) {
                    Icon(
                        imageVector = Icons.Filled.Clear,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            } else {
                IconButton(onClick = {

                }) {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        },
        placeholder = {
            Text(
                text = "Search your movie",
                color = MaterialTheme.colorScheme.onPrimary
            )
        },
        leadingIcon = {
            IconButton(onClick = {
                isSearchVisible.value = false
            }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        )
    )
}