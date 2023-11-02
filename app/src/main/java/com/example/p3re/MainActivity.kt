package com.example.p3re

import android.icu.text.CaseMap.Title
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.p3re.ui.theme.P3RETheme

//Clase per a crear els items de la barra de navegació inferior
data class BottomNavigationItem(
    val title: String,
    //Les imatges que se displayearan depenent de si esta seleccionat o no
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
)



class MainActivity : ComponentActivity() {
    //Para el Scaffold
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            P3RETheme {
                //Lista de items de la barra de navegació inferior
                //Lista de items de la data class creada dalt
                    val items = listOf(
                        BottomNavigationItem(
                            title = "S.Links",
                            //PLACEHOLDER DEL ICONO
                            selectedIcon = Icons.Filled.Home,
                            unselectedIcon = Icons.Outlined.Home,
                        ),
                        BottomNavigationItem(
                            title = "Compendium",
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
                            title = "100% Guide",
                            //PLACEHOLDER DEL ICONO
                            selectedIcon = Icons.Filled.Create,
                            unselectedIcon = Icons.Outlined.Create,
                        )

                    )
                    //LINEA IMPORTANT, esta variable se encarrega de dixar un item seleccionat per default, y asegurarse de que no se cambie
                    var selectedItemIndex by rememberSaveable {
                        //El index per defecte será el primer (0)
                        mutableStateOf(0)
                    }

                Surface  (
                    //Modifier es solo para el tamaño?
                    modifier = Modifier.fillMaxSize(),
                    //Por que .background?
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    //Que es un Scaffold?
                    Scaffold (
                        bottomBar = {
                            //Podria usar un navigationRail per a laterals
                            NavigationBar {
                                //items es el nom de la llista de BottonNavigationItems de la navigation bar (tot creat dalt)
                                //for each, per cada item se mostra el seu corresponent NavigationBarItem depenent del index
                                //index es autoincremental, comença en 0
                                items.forEachIndexed { index, item ->  NavigationBarItem(

                                    //posara el valor per defecte de la variable de dalt
                                    selected = selectedItemIndex == index,
                                    onClick = {
                                        selectedItemIndex = index
                                        navController.navigate()
                                    },
                                    //Nom del item per al menú
                                    label = {
                                      Text(text = item.title)
                                    },
                                    icon = {
                                        Icon (
                                            //si el index coincidix en el item seleccionat per l'usuari (o el default) mostra l'icona en mode seleccionat, si no la no seleccionada
                                            imageVector =
                                            if(index == selectedItemIndex){
                                               item.selectedIcon
                                            }else {
                                              item.unselectedIcon
                                            },
                                            //contentDescription es sobretot por a la accessibilitat, per a que per exemple persones cegues sápiguen on estan navegant dins l'aplicació
                                            //(p.e TalkBack en este cas diría el titol del meu item
                                            contentDescription = item.title,
                                        )
                                    })}

                            }
                        }

                    ) {
                        paddingValues -> paddingValues
                    }
                }
            }
        }
    }
}
