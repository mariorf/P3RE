package com.example.p3re.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.p3re.R
import com.example.p3re.data.Fonts


//https://www.youtube.com/watch?v=lmkEV6TfpaM
//https://stackoverflow.com/questions/76545440/how-to-set-custom-background-color-instead-of-stroke-for-card-in-jetpack-compo
@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun ExpandableCard(title: String, content: String, color: Color){

    var expanded by remember { mutableStateOf(false) }

    Card (
        Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
            .clickable { expanded = !expanded }
        ,colors = CardDefaults.cardColors(
            containerColor = color,
        ), shape = RectangleShape) {
        CardContent(title, content, color, expanded)
    }
}

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
//tengo que pasar boolan para el expanded, porque si no no puedo acceder a toda la card al hacer click
fun CardContent(title: String, expandedContent: String, color: Color, expanded:Boolean) {

    Box(
        modifier = Modifier
            .padding(12.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessLow
                )
            ).background(color)
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
        ) {
            //TITULO
            Text(
                text = title,
                fontFamily = Fonts.summerFontFamily,
                fontSize = 22.sp
            )
            //CONTENIDO
            if (expanded) {
                Text(
                    text = expandedContent,
                    fontFamily = Fonts.summerFontFamily,
                    modifier = Modifier.padding(top = 18.dp),
                    fontSize = 14.sp
                )
            }
        }
    }
}
