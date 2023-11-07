package com.example.p3re.screens

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun FusionCalculatorScreen() {
    val url = "https://arantius.github.io/persona-fusion-calculator/3portable.html#/list/name"

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        AndroidView(factory = {
            WebView(it).apply {
                webViewClient = WebViewClient()
                loadUrl(url)
            }
        }, modifier = Modifier.fillMaxSize())
    }
}

@Preview
@Composable
fun ComposablePreview() {
    FusionCalculatorScreen()
}
