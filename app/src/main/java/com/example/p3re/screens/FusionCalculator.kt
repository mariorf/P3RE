package com.example.p3re.screens

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.p3re.R

@Composable
fun FusionCalculatorScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        //https://stackoverflow.com/questions/68937947/how-to-set-drawable-as-a-background-to-image-in-jetpack-compose
        Image(
            painter = painterResource(R.drawable.prueba1),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            //
        )
        Column(
            modifier = Modifier.fillMaxSize().padding(bottom = 70.dp, top = 70.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Image(
                painter = painterResource(R.drawable.work_in_progress),
                contentDescription = "",
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
fun ComposablePreview() {
    FusionCalculatorScreen()
}
