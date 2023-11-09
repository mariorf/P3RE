package com.example.p3re.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.p3re.data.ShadowViewModel

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
    NavHost(navController, startDestination = Screen.SocialLinks.route,
        enterTransition = { fadeIn(animationSpec = tween(500)) + slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left) },
        exitTransition= { fadeOut(animationSpec = tween(500)) }) {
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
        composable(Screen.DetailedShadow.route) {

            val shadowViewModel = remember { ShadowViewModel() }
            shadowViewModel.shadowReturn()?.let { it1 -> DetailedShadowScreen(it1) }
            shadowViewModel.shadowReturn()?.let { DetailedShadowScreen(it) }
        }
    }
}
