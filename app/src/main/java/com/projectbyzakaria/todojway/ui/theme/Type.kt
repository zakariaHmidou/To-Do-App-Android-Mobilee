package com.projectbyzakaria.todojway.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.projectbyzakaria.todojway.R


val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    , titleMedium = TextStyle(
        color = Color.Black,
        fontFamily = FontFamily(Font(R.font.montserrat_extrabold)),
        fontSize = 30.sp
    ),
    bodyMedium = TextStyle(
        color = Color.Gray,
        fontFamily = FontFamily(Font(R.font.montserrat_light)),
        fontSize = 18.sp
    )
)