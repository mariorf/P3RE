package com.example.p3re.screens

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.room.TypeConverters
import com.example.p3re.R
import com.example.p3re.data.Shadow
import com.example.p3re.data.ShadowDAO
import com.example.p3re.data.ShadowViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.nio.charset.Charset
import java.util.concurrent.Executors


//RECIBEN COMO PARAMETROS NAV CONTROLLERS LAS FUNCIONES A LAS QUE VAS A TENER QUE PODER NAVEGAR

//https://developer.android.com/jetpack/compose/text/fonts
@RequiresApi(Build.VERSION_CODES.Q)
val minervaFamily = FontFamily(
    Font(R.font.minerva_modern_black),
    Font(R.font.minerva_modern_bold_italic),
    Font(R.font.minerva_modern_bold),
    Font(R.font.minerva_modern_italic),
    Font(R.font.minerva_modern_bold_italic)
)

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
@TypeConverters
//Que es este NavHostController
fun CompendiumScreen(navController: NavHostController, context: Context) {

    //Corutine
    /*LaunchedEffect(Unit) {
        shadowDAO.insertAll(shadowsList)
    }*/

    //Leo el archivo aqui porque necesito el contexto que me pasan como parametro
    var json: String?

    //Abro el json con un InputStream
    val inputStream = context.assets.open("shadows.json")

    //BufferedReader para que sea mas rápido
    val reader = BufferedReader(InputStreamReader(inputStream))

    //Creo el string builder
    val jsonStringBuilder = StringBuilder()

    //Linea para ir metiendo al archivo final
    var line: String?

    //append en kotlin para crear el String final
    while (reader.readLine().also { line = it } != null) {
        jsonStringBuilder.append(line)
    }

    //Cierro buffered reader e input string
    reader.close()
    inputStream.close()


    //json final
    json = jsonStringBuilder.toString()

    val gson = Gson()
    val mapType = object : TypeToken<Map<String, Shadow>>() {}.type
    val shadowMap: Map<String, Shadow> = gson.fromJson(json, mapType)

    val shadowsList = shadowMap.values.toList()

    //Recordar el estado del scroll
    val scrollState = rememberLazyListState()

    //Instancia del viewModel
    val shadowViewModel = remember { ShadowViewModel() }

    LazyColumn(


        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(15, 139, 237)),

        //Estado del scroll (no se guarda al navegar con la bottomBar)
        state = scrollState,
        content = {
            items(shadowsList.size) { index ->
                val shadow = shadowsList[index]

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .background(Color(2, 46, 73))
                        .clickable {


                            shadowViewModel.shadowSelector(shadow)
                            navController.navigate(Screen.DetailedShadow.route)
                        }
                ) {
                    //Row dentro de la box principal para añadir el cuadrito de color amarillo
                    //Esta rodea al cuadro amarillo y al texto, si quisiera añadir mas elementos a
                    //cada nombre se añadiría aquí
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,

                        ) {
                        //Esta Box es el cuadrito pequeño
                        Box(
                            modifier = Modifier
                                .size(16.dp)
                                .background(Color(254, 189, 97))
                        )
                        Text(
                            text = shadow.name,
                            color = Color.White,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(start = 8.dp, top = 16.dp, bottom = 16.dp),
                            fontFamily = minervaFamily,
                            fontWeight = FontWeight.Normal,
                        )
                    }
                }
            }
        }
    )
}



/*RequiresApi(Build.VERSION_CODES.Q)
@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {


    CompendiumScreen()
}*/