package com.example.p3re.screens

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.p3re.R
import com.example.p3re.data.Fonts
import com.example.p3re.data.SocialLink
import com.example.p3re.data.SocialLinkBackgroundList
import com.example.p3re.data.SocialLinkSecondaryColorsList
import com.example.p3re.data.SocialLinksImageList
import com.example.p3re.viewmodels.ViewModel

@SuppressLint("SuspiciousIndentation")
@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun DetailedSocialLinkScreen(socialLink: SocialLink, viewModel: ViewModel) {


    //Cargar fondo del personaje
    val backgroundImageList = SocialLinkBackgroundList.loadImages()

    val drawableId =
        backgroundImageList.find { it.second == socialLink.name }?.first ?: R.drawable.untitled


    //cargar imagen del personaje
    val socialLinkImageList = SocialLinksImageList.loadImages()

    val characterImageId =
        socialLinkImageList.find { it.second == socialLink.name }?.first ?: R.drawable.untitled


    //Cambiar color letras topBar
    val secondaryColorsList = SocialLinkSecondaryColorsList.secondayColorsList()

    val secondaryColor = secondaryColorsList.find { it.second == socialLink.name }

    if (secondaryColor != null) {
        viewModel.updateTopBarTextColor(secondaryColor.first)
    }

    Box(modifier = Modifier.fillMaxSize()) {

        //FONDO
        Image(
            painter = painterResource(drawableId),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 70.dp, top = 70.dp, start = 10.dp, end = 10.dp),
            contentPadding = PaddingValues(top = 20.dp)
        ) {
            item {
                if (secondaryColor != null) {
                    Row(
                        modifier = Modifier
                            .height(150.dp)
                            .fillMaxSize()
                            .background(secondaryColor.first)
                            .shadow(10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                        ) {
                            Image(
                                painter = painterResource(characterImageId),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .align(Alignment.Center)
                            )
                        }

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                        ) {

                            Column(
                                Modifier
                                    .fillMaxSize()
                            ) {
                                Column(
                                    Modifier
                                        .fillMaxSize()
                                        .background(secondaryColor.first)
                                ) {
                                    Column(Modifier.padding(8.dp)) {
                                        Text(
                                            text = socialLink.name,
                                            fontFamily = Fonts.summerFontFamily,
                                            fontSize = 26.sp
                                        )
                                        Text(
                                            text = socialLink.arcana,
                                            fontFamily = Fonts.summerFontFamily,
                                            fontSize = 12.sp,
                                            color = Color.Black
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            items(10) { index ->
                if (secondaryColor != null) {
                    ExpandableCard(
                        "Rank " + index,
                        "Respuesta 1:" + index+"\n"+"Respuesta 2: "+index+"\n"+"Respuesta 3:" + index+"\n"+"Respuesta 4: "+index+"\n"+"Respuesta 5:" + index+"\n"+"Respuesta 6: "+index,
                        secondaryColor.first
                    )
                }
            }
        }
    }
    //https://developer.android.com/jetpack/compose/side-effects
    DisposableEffect(Unit) {
        onDispose {
            viewModel.updateTopBarTextColor(Color.Black)
        }
    }
}


