package com.example.p3re

import androidx.annotation.StringRes

sealed class Screen(val route: String, @StringRes val resourceId: Int) {

    //PREGUNTAR CARLES PER SEGON PARAMETRE
    object SocialLinks : Screen("Social Links", R.string.app_name)
    object Compendium : Screen("Compendium", R.string.app_name)
}

