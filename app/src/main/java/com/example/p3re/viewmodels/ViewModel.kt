package com.example.p3re.viewmodels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.p3re.data.Shadow
import com.example.p3re.data.SocialLink
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

//Inicializar en null
var selectedShadow by mutableStateOf<Shadow?>(null)

//Se crea _selectedTabName per a que siga mutable y poder accedir desde Screens
val _selectedTabName = mutableStateOf("SHADOWS")

//Inicializar en null
var selectedSocialLink by mutableStateOf<SocialLink?>(null)

class ViewModel() : ViewModel() {



    fun setShadow(shadow: Shadow) {

        selectedShadow = shadow
    }

    fun getShadow(): Shadow? {
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

    var topBarTextColor by  mutableStateOf(Color(10, 21, 70, 255))
    fun updateTopBarTextColor(updatedColor: Color) {
        topBarTextColor = updatedColor
    }
}
