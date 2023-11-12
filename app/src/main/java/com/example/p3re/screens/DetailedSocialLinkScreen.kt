package com.example.p3re.screens

import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.VectorDrawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.example.p3re.R
import com.example.p3re.data.SocialLink
import com.example.p3re.data.SocialLinkBackgroundList
import com.example.p3re.data.SocialLinksImageList
import com.example.p3re.data.ViewModel

@Composable
fun DetailedSocialLinkScreen(socialLink: SocialLink, viewModel: ViewModel) {


    val backgroundImageList = SocialLinkBackgroundList.loadImages()

    val drawableId = backgroundImageList.find { it.second == socialLink.name }?.first ?: R.drawable.untitled

    val socialLinkImageList = SocialLinksImageList.loadImages()

    val characterImageId = socialLinkImageList.find { it.second == socialLink.name }?.first ?: R.drawable.untitled

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
            //por algun motivo pinta sobre toda la pantalla, no solo en el content del scaffold de la main activty, asi que tengo que poner paddings en top y bottom
            contentPadding = (PaddingValues(bottom = 100.dp, top = 100.dp))
        ) {
            item {
                Image(
                    painter = painterResource(characterImageId),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .size(30.dp)
                )
            }
        }

    }
}
