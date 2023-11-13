package com.example.p3re.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.p3re.R
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavController){


    //Key 1 indica el repintado (??)
    LaunchedEffect(key1 = true){
        delay(3000)
        //Para "borrar" el historial de navegación y no poder volver a la splash screen
        navController.popBackStack()
        navController.navigate(Screen.SocialLinks.route)
    }

    Splash()
}

@Composable
fun Splash(){
    Column (
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.aigis_bg),
            contentDescription = "Splash Screen"
        )
    }

}