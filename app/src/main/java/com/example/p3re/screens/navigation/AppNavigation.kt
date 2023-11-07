package com.example.p3re.screens.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.p3re.screens.CompendiumScreen
import com.example.p3re.screens.SocialLinksScreen


//Esta función se encarga de administrar toda la navegación
//Aquí creo el NavController y NavHost
@Composable
fun AppNavigation() {
    //Todas las pantallas deben de poder acceder al nav controller, es el core de la navegación, ya que es la única que conoce el estado de navegación actual
    val navController = rememberNavController()

    //NavHost, tiene que recibir como parametro un navController y una startDestination (que recibe desde la seales class de AppScreens)
    //Tienes que declarar todas las rutas aqui mediante diferentes composables(que reciben como parametros tambien rutas que se consiguen meidante los pbjetos AppScreens)
    NavHost(navController = navController, startDestination = AppScreens.SocialLinksScreen.route){

        //El composable llama a la pantalla
        composable(route=AppScreens.SocialLinksScreen.route){
            SocialLinksScreen(navController)
        }

        composable(route=AppScreens.CompendiumScreen.route){
            CompendiumScreen(navController)
        }
    }
}