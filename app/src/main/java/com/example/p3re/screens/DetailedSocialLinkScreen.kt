package com.example.p3re.screens

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.VectorDrawable
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.example.p3re.R
import com.example.p3re.data.Fonts
import com.example.p3re.data.SocialLink
import com.example.p3re.data.SocialLinkBackgroundList
import com.example.p3re.data.SocialLinkSecondaryColorsList
import com.example.p3re.data.SocialLinksImageList
import com.example.p3re.data.ViewModel

@SuppressLint("SuspiciousIndentation")
@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun DetailedSocialLinkScreen(socialLink: SocialLink, viewModel: ViewModel) {


    //Cargar fondo del personaje
    val backgroundImageList = SocialLinkBackgroundList.loadImages()

    val drawableId = backgroundImageList.find { it.second == socialLink.name }?.first ?: R.drawable.untitled


    //cargar imagen del personaje
    val socialLinkImageList = SocialLinksImageList.loadImages()

    val characterImageId = socialLinkImageList.find { it.second == socialLink.name }?.first ?: R.drawable.untitled


    //Cambiar color letras topBar
    val secondaryColorsList = SocialLinkSecondaryColorsList.loadColors()

    val secondaryColor = secondaryColorsList.find { it.second == socialLink.name }

    if (secondaryColor != null) {
        viewModel.updateTopBarTextColor(secondaryColor.first)
    }

        Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(drawableId),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
        )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 70.dp, top = 70.dp),
                contentPadding = PaddingValues(bottom = 100.dp, top = 100.dp)
            ) {
                item {
                    Image(
                        painter = painterResource(characterImageId),
                        contentDescription = null,
                        modifier = Modifier
                            .size(400.dp)
                            .fillMaxWidth()

                    )
                }
                items(10) { index ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        val conversation = when (index) {
                            0 -> socialLink.conversation1
                            1 -> socialLink.conversation2
                            2 -> socialLink.conversation3
                            3 -> socialLink.conversation4
                            4 -> socialLink.conversation5
                            5 -> socialLink.conversation6
                            6 -> socialLink.conversation7
                            7 -> socialLink.conversation8
                            8 -> socialLink.conversation9
                            9 -> socialLink.conversation10
                            else -> ""
                        }
                        Text(
                            text = conversation+" "+index,
                            fontWeight = FontWeight.Bold,
                            fontFamily = Fonts.summerFontFamily,
                            fontSize = 18.sp
                        )
                    }
                }
            }
        }
}