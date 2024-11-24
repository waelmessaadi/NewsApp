package com.instant.newsapp.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.instant.newsapp.R

val Roboto = FontFamily(
    Font(R.font.roboto_light, FontWeight.Light),
    Font(R.font.roboto_regular, FontWeight.Normal),
    Font(R.font.roboto_medium, FontWeight.Medium),
    Font(R.font.roboto_bold, FontWeight.Bold),
)

val RobotoCondensed = FontFamily(
    Font(R.font.roboto_condensed_light, FontWeight.Light),
    Font(R.font.roboto_condensed_regular, FontWeight.Normal),
)

var textSizeXLarge = TextStyle(
    fontSize = 24.sp,
    fontFamily = Roboto,
    fontWeight = FontWeight.Bold
)

var textSizeLarge = TextStyle(
    fontSize = 20.sp,
    fontFamily = Roboto,
    fontWeight = FontWeight.Bold
)

val textSizeBase = TextStyle(
    fontSize = 18.sp,
    fontFamily = Roboto,
    fontWeight = FontWeight.SemiBold
)
var textSizeMedium = TextStyle(
    fontSize = 16.sp,
    fontFamily = Roboto,
    fontWeight = FontWeight.Normal
)

val textSizeSmall = TextStyle(
    fontSize = 14.sp,
    fontFamily = RobotoCondensed
)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)