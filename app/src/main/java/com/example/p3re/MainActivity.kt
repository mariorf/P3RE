package com.example.p3re

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.p3re.screens.NavGraph
import com.example.p3re.ui.theme.P3RETheme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.p3re.data.Api
import com.example.p3re.data.SHHADOW
import com.example.p3re.data.ViewModel
import com.example.p3re.screens.Screen
import com.example.p3re.screens.minervaFamily
import com.example.p3re.utils.ShadowsAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


//Clase per a crear els items de la barra de navegació inferior
data class BottomNavigationItem(
    val title: String,
    //Les imatges que se displayearan depenent de si esta seleccionat o no
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
)

class MainActivity : ComponentActivity() {
    //Para el Scaffold

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @RequiresApi(Build.VERSION_CODES.Q)
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            P3RETheme {

                val navController = rememberNavController()
                //Lista de items de la barra de navegació inferior
                //Lista de items de la data class creada dalt
                val itemsNavigationBar = listOf(
                    BottomNavigationItem(
                        title = "S.Links",
                        //PLACEHOLDER DEL ICONO
                        selectedIcon = Icons.Filled.Home,
                        unselectedIcon =Icons.Outlined.Home ,
                    ),
                    BottomNavigationItem(
                        title = "Shadows",
                        //PLACEHOLDER DEL ICONO
                        selectedIcon = Icons.Filled.AccountCircle,
                        unselectedIcon = Icons.Outlined.AccountCircle,
                    ),

                    BottomNavigationItem(
                        title = "Fusion",
                        //PLACEHOLDER DEL ICONO
                        selectedIcon = Icons.Filled.Add,
                        unselectedIcon = Icons.Outlined.Add,
                    ),

                    BottomNavigationItem(
                        title = "Answers",
                        //PLACEHOLDER DEL ICONO
                        selectedIcon = Icons.Filled.Create,
                        unselectedIcon = Icons.Outlined.Create,
                    )

                )

                // Define el ViewModel
                val viewModel: ViewModel = viewModel()

                var selectedItemIndex by rememberSaveable {
                    //El index per defecte será el primer (0)
                    mutableStateOf(0)
                }

                var pagerState = rememberPagerState {
                    //Necesita el numero de tabs
                    itemsNavigationBar.size
                }

                    Scaffold(
                        //TOP BAR
                        topBar = {
                            NavigationBar(
                                containerColor = Color.Transparent
                            ) {
                                //items es el nom de la llista de BottonNavigationItems de la navigation bar (tot creat dalt)
                                //for each, per cada item se mostra el seu corresponent NavigationBarItem depenent del index
                                //index es autoincremental, comença en 0
                                itemsNavigationBar.forEachIndexed { index, itemsNavigationBar ->
                                    NavigationBarItem(
                                        //posara el valor per defecte de la variable de dalt
                                        selected = false,

                                        //Nom del item per al menú (bottom)
                                        label = {
                                            Text(
                                                text = itemsNavigationBar.title.uppercase(),
                                                color = viewModel.topBarTextColor,
                                                fontFamily = minervaFamily,
                                                fontWeight = FontWeight.Normal
                                            )

                                        },

                                        onClick = {

                                            selectedItemIndex = index

                                            //Si el nom del item on click coincideix en alguna de les opcions crida al nav controler y navega hasta ella
                                            when (itemsNavigationBar.title) {
                                                "S.Links" -> {
                                                    navController.navigate(Screen.SocialLinks.route)
                                                }
                                                "Shadows" -> {
                                                    navController.navigate(Screen.Shadows.route)
                                                }
                                                "Fusion" -> {
                                                    navController.navigate(Screen.FusionCalculator.route)
                                                }
                                                "Answers" -> {
                                                    navController.navigate(Screen.Answers.route)
                                                }
                                            }

                                        },

                                        icon = {
                                            Icon(
                                                //si el index coincidix en el item seleccionat per l'usuari (o el default) mostra l'icona en mode seleccionat, si no la no seleccionada
                                                imageVector =
                                                if (index == selectedItemIndex) {
                                                    itemsNavigationBar.selectedIcon
                                                } else {
                                                    itemsNavigationBar.unselectedIcon
                                                },
                                                //contentDescription es sobretot por a la accessibilitat, per a que per exemple persones cegues sápiguen on estan navegant dins l'aplicació
                                                //(p.e TalkBack en este cas diría el titol del meu item
                                                contentDescription = itemsNavigationBar.title,
                                            )
                                        })
                                }
                            }
                        },
                        bottomBar = {
                                Text(
                                    text = viewModel.selectedTabName.value,
                                    fontFamily = minervaFamily,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 40.sp,
                                    color = Color(0, 0, 0, 255),
                                )
                        }


                        //BOTTOM BAR
                        /*bottomBar = {

                            //Podria usar un navigationRail per a laterals
                            NavigationBar(
                                //containerColor = Color(48, 62, 140)
                            ) {
                                //items es el nom de la llista de BottonNavigationItems de la navigation bar (tot creat dalt)
                                //for each, per cada item se mostra el seu corresponent NavigationBarItem depenent del index
                                //index es autoincremental, comença en 0
                                itemsNavigationBar.forEachIndexed { index, itemsNavigationBar ->
                                    NavigationBarItem(
                                        //posara el valor per defecte de la variable de dalt
                                        selected = false,

                                        //Nom del item per al menú (bottom)
                                        label = {
                                            Text(
                                                text = itemsNavigationBar.title,
                                                color = Color.White,
                                                fontFamily = minervaFamily,
                                                fontWeight = FontWeight.Normal
                                            )

                                        },

                                        onClick = {

                                            selectedItemIndex = index

                                            //Si el nom del item on click coincideix en alguna de les opcions crida al nav controler y navega hasta ella
                                            when (itemsNavigationBar.title) {
                                                "S.Links" -> {
                                                    navController.navigate(Screen.SocialLinks.route)
                                                }
                                                "Shadows" -> {
                                                    navController.navigate(Screen.Shadows.route)
                                                }
                                                "Fusion Calc" -> {
                                                    navController.navigate(Screen.FusionCalculator.route)
                                                }
                                                "Answers" -> {
                                                    navController.navigate(Screen.Answers.route)
                                                }
                                            }

                                        },

                                        icon = {
                                            Icon(
                                                //si el index coincidix en el item seleccionat per l'usuari (o el default) mostra l'icona en mode seleccionat, si no la no seleccionada
                                                imageVector =
                                                if (index == selectedItemIndex) {
                                                    itemsNavigationBar.selectedIcon
                                                } else {
                                                    itemsNavigationBar.unselectedIcon
                                                },
                                                //contentDescription es sobretot por a la accessibilitat, per a que per exemple persones cegues sápiguen on estan navegant dins l'aplicació
                                                //(p.e TalkBack en este cas diría el titol del meu item
                                                contentDescription = itemsNavigationBar.title,
                                            )
                                        })
                                }
                            }
                        },*/


                    ) {
                        NavGraph(navController, viewModel, this)
                    }
                }
            }
        }
    }
