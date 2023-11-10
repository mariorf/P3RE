package com.example.p3re.data

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.p3re.data.Shadow

var selectedShadow by mutableStateOf<Shadow?>(null)

class ShadowViewModel() : ViewModel() {

    //Se crea _selectedTabName per a que siga mutable y poder accedir desde Screens
    private val _selectedTabName = mutableStateOf("SOCIAL LINKS")
    fun shadowSelector(shadow: Shadow) {

        selectedShadow = shadow
    }

    fun shadowReturn(): Shadow? {
        return selectedShadow
    }

    //State basicament es un estat o valor que Compose pot vore en tot moment
    //Se cambia el valor segons _selectedTabName, que se actualitza on vuigues amb el metode de baix
    val selectedTabName: State<String> = _selectedTabName

    fun updateSelectedTabName(updatedName: String) {
        _selectedTabName.value = updatedName
    }
}
