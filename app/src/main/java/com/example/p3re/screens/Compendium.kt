package com.example.p3re.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

//RECIBEN COMO PARAMETROS NAV CONTROLLERS LAS FUNCIONES A LAS QUE VAS A TENER QUE PODER NAVEGAR
@Composable
fun CompendiumScreen(navController: NavController) {

    LazyColumn(
        Modifier
            .background(color = Color.Cyan)
            .fillMaxSize()
            .background(color = Color(63, 97, 166))

    ) {
        items(40) { index ->
            Text(text = "Item: $index")
        }
    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {

    val navController = rememberNavController()

    CompendiumScreen(navController)
}