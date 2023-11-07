package com.example.p3re

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



@Composable
fun CompendiumScreen() {

    LazyColumn(
        Modifier.padding(top = 80.dp, bottom = 130.dp).background(color = Color.Red).fillMaxSize()
    ) {
        items(2) { index ->
            Text(text = "Item: $index")
        }
    }
}


@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {

    CompendiumScreen()
}