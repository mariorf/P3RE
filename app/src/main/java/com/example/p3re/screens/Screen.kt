package com.example.p3re.screens

import DetailedShadowScreen
import android.app.Application
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.p3re.data.ShadowDAO
import com.example.p3re.data.ShadowViewModel

sealed class Screen(val route: String){
    object SocialLinks : Screen("social_links")
    object Shadows : Screen("shadows")
    object FusionCalculator : Screen("fusion_calculator")
    object Answers : Screen("answers")

    object DetailedShadow : Screen("detailed_shadow")


}
//el navController es necesario para navegar entre pantallas
//View model para poder mover datos entre pantallas
//Parametro context para poder pasar el contexto de la main activity y por tanto de toda la app
@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun NavGraph(navController: NavHostController, viewModel: ShadowViewModel, context: Context) {
    NavHost(
        navController,
        startDestination = Screen.SocialLinks.route,
        enterTransition = { fadeIn(animationSpec = tween(0)) },
        exitTransition= { fadeOut(animationSpec = tween(0)) }) {
        composable(Screen.SocialLinks.route) {
            viewModel.updateSelectedTabName("SOCIAL LINKS")
            SocialLinksScreen()
        }
        composable(Screen.Shadows.route) {
            viewModel.updateSelectedTabName("SHADOWS")
            CompendiumScreen(navController, context)
        }
        composable(Screen.FusionCalculator.route) {
            viewModel.updateSelectedTabName("FUSION CALCULATOR")
            FusionCalculatorScreen()
        }
        composable(Screen.Answers.route) {
            viewModel.updateSelectedTabName("ANSWERS")
            AnswersScreen()
        }
        composable(Screen.DetailedShadow.route) {
            val shadowViewModel = remember { ShadowViewModel() }
            shadowViewModel.shadowReturn()?.let { it1 -> DetailedShadowScreen(it1) }
            shadowViewModel.shadowReturn()?.let { DetailedShadowScreen(it) }
        }
    }
}


