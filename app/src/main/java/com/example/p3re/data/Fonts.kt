package com.example.p3re.data

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.example.p3re.R

object Fonts {

    @RequiresApi(Build.VERSION_CODES.Q)
    val summerFontFamily = FontFamily(
    Font(R.font.summer_sans_regular),

    )
    @RequiresApi(Build.VERSION_CODES.Q)
    val minervaFamily = FontFamily(
        Font(R.font.minerva_modern_black),
        Font(R.font.minerva_modern_bold_italic),
        Font(R.font.minerva_modern_bold),
        Font(R.font.minerva_modern_italic),
        Font(R.font.minerva_modern_bold_italic)
    )
}