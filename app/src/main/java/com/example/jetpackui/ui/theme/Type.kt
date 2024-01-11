package com.example.jetpackui.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.JetPackUI.R


val gilroyFontBold = FontFamily(
    Font(R.font.gilroy_bold_w700, FontWeight.Bold),
    Font(R.font.gilroy_semi_bold_w600, FontWeight.SemiBold)
)

val gilroyFontNormal = FontFamily(
    Font(R.font.gilroy_regular_w400, FontWeight.Normal),
    Font(R.font.gilroy_medium_w500, FontWeight.Medium)
)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)