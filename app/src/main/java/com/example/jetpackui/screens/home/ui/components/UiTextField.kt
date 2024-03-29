package com.example.jetpackui.screens.home.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackui.ui.theme.AppTheme
import com.example.jetpackui.ui.theme.gilroyFontNormal

/**
 * Created by Asieuzzaman Wasir on 12,January,2024
 */
@Composable
fun UserInputField(
    hint: String = "Enter your input here",
    value: String,
    isError: Boolean,
    isPassWord: Boolean,
    onChange: (String) -> Unit,
    submit: () -> Unit,
) {
    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        value = value,
        onValueChange = onChange,
        placeholder = {
            Text(
                text = hint,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = gilroyFontNormal,
                    color = Color.White
                )
            )
        },

        textStyle = TextStyle(
            textAlign = TextAlign.Start,
            color = Color.White,
            fontWeight = FontWeight.Light
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(
            onNext = { focusManager.moveFocus(FocusDirection.Down) },
            onDone = { submit() }
        ),
        isError = isError,
        supportingText = {
            if (isError) {
                if (isPassWord) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 0.dp),
                        text = "Please enter your password",
                        color = MaterialTheme.colorScheme.error
                    )
                } else {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 0.dp),
                        text = "Please enter valid email",
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        },
        visualTransformation = if (isPassWord) PasswordVisualTransformation() else VisualTransformation.None
    )
}


@Composable
fun NormalText(value: String, textAlign: TextAlign) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 80.dp),
        style = TextStyle(
            fontSize = 18.sp,
            fontFamily = gilroyFontNormal,
            fontWeight = FontWeight(400),
            color = Color.White
        ),
        textAlign = textAlign
    )
}

@Preview(showBackground = false)
@Composable
fun PreviewTextFields() {
    AppTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            UserInputField(hint = "Please your email",
                isPassWord = false,
                value = "sdfds",
                isError = true,
                onChange = {
                },
                submit = {

                })
            Spacer(Modifier.height(16.dp))

            NormalText(value = "font", TextAlign.Start)
        }
    }
}