package com.example.p3re

import android.annotation.SuppressLint
import android.content.Context
import android.icu.text.CaseMap.Title
import android.os.Build
import android.os.Bundle
import android.text.style.BackgroundColorSpan
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.VectorConverter
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.*
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.ListItemDefaults.contentColor
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.p3re.screens.CompendiumScreen
import com.example.p3re.screens.FusionCalculatorScreen
import com.example.p3re.screens.NavGraph
import com.example.p3re.screens.Screen
import com.example.p3re.screens.SocialLinksScreen
import com.example.p3re.screens.minervaFamily
import com.example.p3re.ui.theme.P3RETheme
import com.google.gson.Gson
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.text.SimpleDateFormat
import java.util.Date
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.example.p3re.data.ShadowDatabase
import com.example.p3re.data.ShadowViewModel


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
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            P3RETheme {

                val baseDeDatos = Room.databaseBuilder(this, ShadowDatabase::class.java, "nombre_de_la_base_de_datos").build()
                val tuDao = baseDeDatos.shadowDao

                val navController = rememberNavController()
                //Lista de items de la barra de navegació inferior
                //Lista de items de la data class creada dalt
                val itemsNavigationBar = listOf(
                    BottomNavigationItem(
                        title = "S.Links",
                        //PLACEHOLDER DEL ICONO
                        selectedIcon = Icons.Filled.Home,
                        unselectedIcon = Icons.Outlined.Home,
                    ),
                    BottomNavigationItem(
                        title = "Shadows",
                        //PLACEHOLDER DEL ICONO
                        selectedIcon = Icons.Filled.AccountCircle,
                        unselectedIcon = Icons.Outlined.AccountCircle,
                    ),

                    BottomNavigationItem(
                        title = "Fusion Calc",
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
                val viewModel: ShadowViewModel = viewModel()

                var selectedItemIndex by rememberSaveable {
                    //El index per defecte será el primer (0)
                    mutableStateOf(0)
                }

                var selectedTabNameTop by rememberSaveable {
                    mutableStateOf("SOCIAL LINKS")
                }

                var currentDate: Date = Date()

                Surface(
                ) {
                    Scaffold(
                        //TOP BAR
                        topBar = {
                            TopAppBar(
                                title = {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        //Que hace esto exactamente?
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = selectedTabNameTop,
                                            fontFamily = minervaFamily,
                                            fontWeight = FontWeight.Normal
                                        )
                                        Column(
                                            horizontalAlignment = Alignment.End,
                                            modifier = Modifier.padding(end = 16.dp)
                                        ) {
                                            Text(
                                                text = "Evening",
                                                fontFamily = minervaFamily,
                                                fontWeight = FontWeight.Normal,
                                            )
                                            Text(
                                                text = SimpleDateFormat("MM/dd").format(currentDate),
                                                fontFamily = minervaFamily,
                                                fontWeight = FontWeight.Normal,
                                            )
                                        }
                                    }
                                },
                                //https://stackoverflow.com/questions/73982907/compose-topappbar-has-no-background-color
                                colors = TopAppBarDefaults.smallTopAppBarColors(
                                    containerColor = Color(254, 201, 97)
                                )
                            )
                        },


                        //BOTTOM BAR
                        bottomBar = {
                            //Podria usar un navigationRail per a laterals
                            NavigationBar(
                                containerColor = Color(48, 62, 140)
                            ) {
                                //items es el nom de la llista de BottonNavigationItems de la navigation bar (tot creat dalt)
                                //for each, per cada item se mostra el seu corresponent NavigationBarItem depenent del index
                                //index es autoincremental, comença en 0
                                itemsNavigationBar.forEachIndexed { index, itemsNavigationBar ->
                                    NavigationBarItem(
                                        //posara el valor per defecte de la variable de dalt
                                        selected = selectedItemIndex == index,

                                        onClick = {
                                            selectedItemIndex = index
                                            //Busca els items per el nom exacte (el .title vamos) y cambia el nom de la topBar
                                            if (itemsNavigationBar.title == "S.Links") {
                                                selectedTabNameTop = "SOCIAL LINKS"
                                                navController.navigate(Screen.SocialLinks.route)
                                            }
                                            if (itemsNavigationBar.title == "Shadows") {
                                                selectedTabNameTop = "SHADOWS"
                                                navController.navigate(Screen.Shadows.route)
                                            }
                                            if (itemsNavigationBar.title == "Fusion Calc") {
                                                selectedTabNameTop = "FUSION CALCULATOR"
                                                navController.navigate(Screen.FusionCalculator.route)
                                            }
                                            if (itemsNavigationBar.title == "Answers") {
                                                selectedTabNameTop = "CLASS ANSWERS"
                                                navController.navigate(Screen.Guide.route)
                                            }
                                        },
                                        //Nom del item per al menú
                                        label = {

                                            Text(
                                                text = itemsNavigationBar.title,
                                                color = Color.White,
                                                fontFamily = minervaFamily,
                                                fontWeight = FontWeight.Normal
                                            )

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
                    ) {
                        NavGraph(navController)
                    }
                }
            }
        }
    }
}
