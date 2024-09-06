package com.zekony.windichat.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.zekony.windichat.R

val roboto_font_family = FontFamily(
    listOf(
        Font(R.font.roboto_light, FontWeight.Light),
        Font(R.font.roboto_bold, FontWeight.SemiBold),
        Font(R.font.roboto_medium, FontWeight.Normal),
    )
)
// Set of Material typography styles to start with
val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = roboto_font_family,
        fontWeight = FontWeight.SemiBold,
        fontSize = 53.sp,
        lineHeight = 50.sp,
        letterSpacing = 0.sp
    ),
    displayMedium = TextStyle(
        fontFamily = roboto_font_family,
        fontWeight = FontWeight.SemiBold,
        fontSize = 40.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = roboto_font_family,
        fontWeight = FontWeight.Medium,
        fontSize = 26.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = roboto_font_family,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
    ),
    bodySmall = TextStyle(
        fontFamily = roboto_font_family,
        fontWeight = FontWeight.Light,
        fontSize = 14.sp,
    ),
    titleLarge = TextStyle(
        fontFamily = roboto_font_family,
        fontWeight = FontWeight.SemiBold,
        fontSize = 26.sp
    ),
    titleMedium = TextStyle(
        fontFamily = roboto_font_family,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp
    ),
    labelMedium = TextStyle(
        fontFamily = roboto_font_family,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    labelSmall = TextStyle(
        fontFamily = roboto_font_family,
        fontWeight = FontWeight.Normal,
        fontSize = 11.sp
    )
)