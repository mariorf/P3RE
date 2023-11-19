package com.example.p3re.screens

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.p3re.R
import com.example.p3re.data.Fonts
import com.example.p3re.data.SocialLink
import com.example.p3re.data.SocialLinksImageList
import com.example.p3re.utils.WindowInfo
import com.example.p3re.utils.rememberWindowInfo
import com.example.p3re.viewmodels.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader


//https://developer.android.com/jetpack/compose/lists
@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun SocialLinksScreen(navController: NavHostController, context: Context) {

    val viewModel = remember { ViewModel() }

    val imageList = SocialLinksImageList.loadImages()

    //Leo el archivo aqui porque necesito el contexto que me pasan como parametro
    var json: String?

    //Abro el json con un InputStream
    val inputStream = context.assets.open("social_links.json")

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
    val mapType = object : TypeToken<Map<String, SocialLink>>() {}.type
    val socialLinksMap: Map<String, SocialLink> = gson.fromJson(json, mapType)

    val socialLinksList = socialLinksMap.values.toList()


    //Recordar el estado del scroll
    val scrollState = rememberLazyGridState()

    val windowInfo = rememberWindowInfo()

    if(windowInfo.screenWidthInfo == WindowInfo.WindowType.Small || windowInfo.screenWidthInfo == WindowInfo.WindowType.Medium) {

        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(R.drawable.prueba1),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
            )

            LazyVerticalGrid(
                modifier = Modifier.padding(bottom = 70.dp, top = 70.dp),
                columns = GridCells.Adaptive(120.dp),
                state = scrollState,
                content = {

                    items(socialLinksList.size) { index ->
                        val (imageResource, imageName) = imageList[index]
                        val socialLink = socialLinksList[index]
                        Image(
                            painter = painterResource(imageResource),
                            contentDescription = null,
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier
                                .fillMaxSize()
                                //AspectRatio fijo de la imagen, tendré que hacer las imagenes de exactamenmte la misma resolucion
                                //width/height para el aspect ratio
                                .aspectRatio(0.75f) // Maintain aspect ratio
                                .padding(8.dp)
                                .background(Color.White)
                                //.border(2.dp, color = Color(10, 21, 70, 255))
                                .clickable {

                                    viewModel.setSocialLink(socialLink)
                                    navController.navigate(Screen.DetailedSocialLink.route)
                                }
                        )
                    }
                }
            )
        }
    } else {

        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(R.drawable.prueba1),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 70.dp, bottom = 70.dp)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {
                    items(socialLinksList.size) { index ->
                        val socialLink = socialLinksList[index]
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 10.dp)
                                .clickable{

                                    viewModel.setSocialLink(socialLink)
                                },
                            verticalAlignment = Alignment.CenterVertically,

                            ) {
                            Text(
                                text = socialLink.name,
                                color = Color(10, 21, 70, 255),
                                fontSize = 18.sp,
                                modifier = Modifier.padding(
                                    start = 8.dp,
                                    top = 26.dp,
                                    bottom = 26.dp
                                ),
                                fontFamily = Fonts.summerFontFamily,
                                fontWeight = FontWeight.Normal,
                            )
                        }

                    }
                }
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {
                    viewModel.getSocialLink()
                        ?.let { DetailedSocialLinkScreen(socialLink = it, viewModel = viewModel) }
                }
            }
        }
    }
}






