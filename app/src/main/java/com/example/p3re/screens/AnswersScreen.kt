package com.example.p3re.screens

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.p3re.R
import com.example.p3re.data.Answers
import com.example.p3re.data.Fonts
import com.example.p3re.viewmodels.AnswersdbViewModel
import com.example.p3re.viewmodels.ShadowsdbViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun AnswersScreen(context: Context) {

    val viewModelAnswersdb : AnswersdbViewModel = viewModel()

    var answersList = viewModelAnswersdb.answersList

    Box(Modifier.fillMaxSize()){

        Image(
            painter = painterResource(R.drawable.prueba1),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )
        LazyColumn(modifier = Modifier.padding(top = 70.dp, bottom = 70.dp),
            content = {
                items(answersList.size) { index ->
                    val question = answersList[index]

                    //SI USAS UNA COLUMNA SE STACKEAN VERTICALMENTE
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Transparent)
                            .border(
                                border = BorderStroke(width = 1.dp, color = Color.Black),
                                shape = RectangleShape
                            )
                            .padding(vertical = 30.dp)
                    ) {
                        Text(
                            text = question.date,
                            color = Color.Black,
                            fontSize = 36.sp,
                            fontFamily = Fonts.summerFontFamily,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp)
                        )
                        Text(
                            text = "Q: "+question.Question,
                            color = Color.Black,
                            fontSize = 18.sp,
                            fontFamily = Fonts.neulisFamily,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp, vertical = 8.dp)
                        )
                        Text(
                            text = "A: "+question.Answer,
                            color = Color.Black,
                            fontSize = 18.sp,
                            fontFamily = Fonts.neulisFamily,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp, vertical = 8.dp)
                        )
                    }

                }
            })
    }
}