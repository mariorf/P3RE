package com.example.p3re.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.p3re.R
import com.example.p3re.data.Shadow

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun DetailedShadowScreen(shadow: Shadow) {
    Box(
        modifier = Modifier.fillMaxSize().background(Color(15, 139, 237)),
    ) {
        Text(
            text = shadow.name.uppercase(),
            color = Color(254, 201, 97),
            modifier = Modifier.padding(top = 50.dp),
            fontFamily = minervaFamily,
            fontStyle = FontStyle.Italic,
            fontSize = 40.sp
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(R.drawable.cowardly_maya),
                contentDescription = shadow.name,
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 150.dp)
            )

            Text(
                text = shadow.race + ", " + shadow.name,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                fontFamily = minervaFamily,
                color = Color.White,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

