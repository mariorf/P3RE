package com.example.p3re.screens

import android.os.Debug
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.p3re.R
import java.io.File

//RECIBEN COMO PARAMETROS NAV CONTROLLERS LAS FUNCIONES A LAS QUE VAS A TENER QUE PODER NAVEGAR
@Composable
fun SocialLinksScreen() {

    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "socialLinks")
    }
}


@Composable
@Preview(showBackground = true)
fun SocialLinksScreenPreview() {

    val navController = rememberNavController()

    SocialLinksScreen()
}