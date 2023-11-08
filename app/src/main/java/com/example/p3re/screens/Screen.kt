package com.example.p3re.screens

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.p3re.ShadowsData

sealed class Screen(val route: String){
    object SocialLinks : Screen("social_links")
    object Shadows : Screen("shadows")
    object FusionCalculator : Screen("fusion_calculator")
    object Guide : Screen("guide")

    object DetailedShadow : Screen("detailed_shadow")

}
@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = Screen.SocialLinks.route) {
        composable(Screen.SocialLinks.route) {
            SocialLinksScreen()
        }
        composable(Screen.Shadows.route) {
            CompendiumScreen(navController)
        }
        composable(Screen.FusionCalculator.route) {
            FusionCalculatorScreen()
        }
        composable(Screen.Guide.route) {
            //GuideScreen()
        }
        composable(Screen.DetailedShadow.route) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            val shadow = shadowsList.find { it.name == name } ?: ShadowsData("NOT FOUND", "NOT FOUND", "NOT FOUND", 0, "NOT FOUND", "NOT FOUND", arrayDeStrings, arrayDeInts) // O un valor predeterminado válido
            DetailedShadowScreen(shadow)
        }
    }
}

// Array de strings
val arrayDeStrings = listOf("Manzana", "Banana", "Cereza", "Damasco")

// Array de ints
val arrayDeInts = listOf(1, 2, 3, 4, 5)

