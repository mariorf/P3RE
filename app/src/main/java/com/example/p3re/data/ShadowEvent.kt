package com.example.p3re.data

sealed interface ShadowEvent {
    data class InsertShadow(val shadow:SHHADOW): ShadowEvent
}
