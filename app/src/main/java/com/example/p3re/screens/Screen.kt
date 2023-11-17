package com.example.p3re.screens

import DetailedShadowScreen
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.p3re.viewmodels.ViewModel
import com.example.p3re.viewmodels.selectedSocialLink

sealed class Screen(val route: String){
    object SocialLinks : Screen("social_links")
    object Shadows : Screen("shadows")
    object FusionCalculator : Screen("fusion_calculator")
    object Answers : Screen("answers")

    object DetailedShadow : Screen("detailed_shadow")

    object DetailedSocialLink : Screen("detailed_social_link")

    object SplashScreen : Screen("splash_screen")

}
//el navController es necesario para navegar entre pantallas
//View model para poder mover datos entre pantallas
//Parametro context para poder pasar el contexto de la main activity y por tanto de toda la app
@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun NavGraph(
    navController: NavHostController,
    viewModel: ViewModel,
    context: Context,
) {
    NavHost(
        navController,
        startDestination = Screen.SocialLinks.route,
        enterTransition = { fadeIn(animationSpec = tween(0)) },
        exitTransition= { fadeOut(animationSpec = tween(0)) }) {

        composable(Screen.SocialLinks.route) {
            viewModel.updateSelectedTabName("SOCIAL LINKS")
            SocialLinksScreen(navController, context)
        }
        composable(Screen.Shadows.route) {
            viewModel.updateSelectedTabName("SHADOWS")
            CompendiumScreen(navController, context)
        }
        composable(Screen.FusionCalculator.route) {
            viewModel.updateSelectedTabName("FUSION")
            FusionCalculatorScreen()
        }
        composable(Screen.Answers.route) {
            viewModel.updateSelectedTabName("ANSWERS")
            AnswersScreen(context)
        }
        composable(Screen.DetailedShadow.route) {
            /*DetailedShadowScreen(shadow = shadowViewModel.getShadow())*/
            viewModel.getShadow()?.let { it1 -> DetailedShadowScreen(shadow = it1) }
        }
        composable(Screen.DetailedSocialLink.route){
            //Poner el nombre del social link abajo
            //viewModel.updateSelectedTabName(selectedSocialLink.name.split(" ")[0].uppercase())
            selectedSocialLink?.name?.split(" ")?.get(0)?.let { it1 ->
                viewModel.updateSelectedTabName(
                    it1.uppercase())
            }
            //Navegar con parametro social link y viewmodel
            viewModel.getSocialLink()?.let { it1 -> DetailedSocialLinkScreen(socialLink = it1, viewModel = viewModel) }
        }
    }
}


