package com.example.p3re.data

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel

//Inicializar en null
var selectedShadow by mutableStateOf<Shadows?>(null)

//Se crea _selectedTabName per a que siga mutable y poder accedir desde Screens
val _selectedTabName = mutableStateOf("SHADOWS")

//Inicializar en null
var selectedSocialLink by mutableStateOf<SocialLink?>(null)


class ViewModel() : ViewModel() {

    fun setShadow(shadow: Shadows) {

        selectedShadow = shadow
    }

    fun getShadow(): Shadows? {
        return selectedShadow
    }

    fun setSocialLink(socialLink: SocialLink) {

        selectedSocialLink = socialLink
    }

    fun getSocialLink(): SocialLink? {
        return selectedSocialLink
    }

    //State basicament es un estat o valor que Compose pot vore en tot moment
//Se cambia el valor segons _selectedTabName, que se actualitza on vuigues amb el metode de baix
    val selectedTabName: State<String> = _selectedTabName

    fun updateSelectedTabName(updatedName: String) {
        _selectedTabName.value = updatedName
    }

    var topBarTextColor by  mutableStateOf(Color(66, 238, 239))
    fun updateTopBarTextColor(updatedColor: Color){
        topBarTextColor = updatedColor
    }


}
