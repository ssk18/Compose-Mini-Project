@file:OptIn(ExperimentalTextApi::class)

package com.ssk.composeminiprojects.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontVariation
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ssk.composeminiprojects.R

val MontserratMedium = FontFamily(
    Font(
        resId = R.font.montserrat_variablefont_wght,
        variationSettings = FontVariation.Settings(
            FontVariation.weight(500)
        )
    )
)
val MontserratSmall = FontFamily(
    Font(
        resId = R.font.montserrat_variablefont_wght,
        variationSettings = FontVariation.Settings(
            FontVariation.weight(600)
        )
    )
)

val PoltawskyMedium = FontFamily(
    Font(
        resId = R.font.poltawskinowy_variablefont_wght,
        variationSettings = FontVariation.Settings(
            FontVariation.weight(700)
        )
    )
)


// Set of Material typography styles to start with
val Typography = Typography(
    titleSmall = TextStyle(
        fontFamily = MontserratMedium,
        fontSize = 17.sp,
        lineHeight = 24.sp
    ),
    bodySmall = TextStyle(
        fontFamily = MontserratSmall,
        fontSize = 15.sp,
        lineHeight = 24.sp

    ),
    titleLarge = TextStyle(
        fontFamily = PoltawskyMedium,
        fontSize = 22.sp,
        lineHeight = 28.sp

    ),
    bodyLarge = TextStyle(
        fontFamily = PoltawskyMedium,
        fontSize = 32.sp,
        lineHeight = 36.sp
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