package com.example.p3re.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.p3re.ShadowsData
import java.io.File
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken




val jsonPath = "app/src/main/java/com/example/p3re/van-enemy-data.json"

val jsonContent = File(jsonPath).readText()

val gson = Gson()
val mapType = object : TypeToken<Map<String, ShadowsData>>() {}.type
val shadowsMap: Map<String, ShadowsData> = gson.fromJson(jsonContent, mapType)

val shadowsList = shadowsMap.values.toList()

@Composable
fun CompendiumScreen() {
    LazyColumn(
        modifier = Modifier.fillMaxSize().background(color = Color(63, 97, 166)),
        content = {
            items(shadowsList.size) { index ->
                val shadowData = shadowsList[index]
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .background(Color.Red)
                ) {
                    Text(
                        text = "Name: ${shadowData.name}",
                        color = Color.White,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    )
}


@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {

    val navController = rememberNavController()
    CompendiumScreen()
}