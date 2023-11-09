package com.example.p3re.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.p3re.data.Shadow

var selectedShadow by mutableStateOf<Shadow?>(null)

class ShadowViewModel() : ViewModel() {
    fun shadowSelector(shadow: Shadow) {

        selectedShadow = shadow
    }

    fun shadowReturn(): Shadow? {
        return selectedShadow
    }
}
