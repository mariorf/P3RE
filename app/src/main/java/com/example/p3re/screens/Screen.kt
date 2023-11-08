package com.example.p3re.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

sealed class Screen(val route: String){
    object SocialLinks : Screen("social_links")
    object Shadows : Screen("shadows")
    object FusionCalculator : Screen("fusion_calculator")
    object Guide : Screen("guide")

}
@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = Screen.SocialLinks.route) {
        composable(Screen.SocialLinks.route) {
            // Llama a tu Composable de Social Links
            SocialLinksScreen()
        }
        composable(Screen.Shadows.route) {
            // Llama a tu Composable de Shadows
            CompendiumScreen()
        }
        composable(Screen.FusionCalculator.route) {
            // Llama a tu Composable de Fusion Calculator
            FusionCalculatorScreen()
        }
        composable(Screen.Guide.route) {
            // Llama a tu Composable de 100% Guide
            //GuideScreen()
        }
    }
}
