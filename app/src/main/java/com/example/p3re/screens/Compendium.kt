package com.example.p3re.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.navigation.NavHostController
import androidx.room.TypeConverters
import com.example.p3re.R
import com.example.p3re.data.Fonts
import com.example.p3re.viewmodels.ViewModel
import java.util.concurrent.Executors
import android.os.Handler
import android.os.Looper
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModelProvider
import com.example.p3re.data.Shadow
import com.example.p3re.apis.ShadowsAPI
import com.example.p3re.database.ShadowDAO
import com.example.p3re.viewmodels.ShadowsdbViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.ExecutorService


//RECIBEN COMO PARAMETROS NAV CONTROLLERS LAS FUNCIONES A LAS QUE VAS A TENER QUE PODER NAVEGAR

//https://developer.android.com/jetpack/compose/text/fonts


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
@TypeConverters
//Que es este NavHostController
fun CompendiumScreen(navController: NavHostController) {


    //Esto debería hacerse en el view model
    val scrollState = rememberLazyListState()

    val viewModel: ViewModel = viewModel()

    val executor = Executors.newSingleThreadExecutor()

    val handler = Handler(Looper.getMainLooper())

    //var shadowsList by remember { mutableStateOf(ArrayList<Shadow>()) }

    val viewModelShadowDatabase : ShadowsdbViewModel = viewModel()

    //val shadowViewModel = ViewModelProvider(this).get(ShadowsdbViewModel::class.java)

    /*executor.execute {
        val api = ShadowsAPI()
        val result = api.getShadows()
        handler.post {
            result?.let { shadows ->
                shadowsList = shadows
                shadowViewModel.insertShadows(shadowsList)
                val allShadows = shadowViewModel.getAllShadows()
            }
        }
    }*/



    var shadowsList = viewModelShadowDatabase.shadowsList

    //BOX HACE QUE LOS ELEMENTOS SE PUEDAN SOBREPONER, POR ESO ES NECESARIA PAR HACER BACKGROUNDS
    Box(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(R.drawable.prueba1),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 70.dp, top = 70.dp),
            //Estado del scroll (no se guarda al navegar con la bottomBar)
            state = scrollState,



            content = {
                items(shadowsList.size) { index ->
                    val shadow = shadowsList[index]
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Transparent)
                            .border(
                                border = BorderStroke(width = 1.dp, color = Color.Black),
                                shape = RectangleShape
                            )
                            .clickable {


                                viewModel.setShadow(shadow)
                                navController.navigate(Screen.DetailedShadow.route)
                            },


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
                            /*Box(
                                modifier = Modifier
                                    .background(Color(9, 45, 197))
                                    .size(16.dp)
                            )*/
                            Text(
                                text = shadow.name.uppercase(),
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
            }
        )
    }
}


/*RequiresApi(Build.VERSION_CODES.Q)
@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {


    CompendiumScreen()
}*/