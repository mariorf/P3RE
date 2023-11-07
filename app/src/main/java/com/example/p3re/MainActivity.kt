package com.example.p3re

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.p3re.screens.CompendiumScreen
import com.example.p3re.screens.FusionCalculatorScreen
import com.example.p3re.screens.SocialLinksScreen
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
    @RequiresApi(Build.VERSION_CODES.Q)
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MainScreen(navController = rememberNavController())
        }
    }
}
@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController) {
    P3RETheme {

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

        var selectedItemIndex by rememberSaveable {
            //El index per defecte será el primer (0)
            mutableStateOf(0)
        }

        var selectedTabName by rememberSaveable {
            mutableStateOf("Social Links")
        }

        Surface(
            modifier = Modifier.fillMaxSize(),

            ) {
            Scaffold(
                //TOP BAR
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                text = selectedTabName,
                                color = Color(254, 189, 97))
                        },
                        //https://stackoverflow.com/questions/73982907/compose-topappbar-has-no-background-color
                        colors = TopAppBarDefaults.smallTopAppBarColors(
                            containerColor = Color(0, 41, 63)
                        )
                    )
                },


                //BOTTOM BAR
                bottomBar = {
                    //Podria usar un navigationRail per a laterals
                    NavigationBar(
                        containerColor = Color(0, 41, 63)
                    ) {
                        //LOOP ITEMS
                        //items es el nom de la llista de BottonNavigationItems de la navigation bar (tot creat dalt)
                        //for each, per cada item se mostra el seu corresponent NavigationBarItem depenent del index
                        //index es autoincremental, comença en 0
                        itemsNavigationBar.forEachIndexed { index, itemsNavigationBar ->
                            NavigationBarItem(

                                //posara el valor per defecte de la variable de dalt
                                selected = selectedItemIndex == index,
                                onClick = {
                                    selectedItemIndex = index
                                    //Busca els items per el nom exacte (el .title vamos)
                                    if (itemsNavigationBar.title == "S.Links") {
                                        selectedTabName = "Social Links"
                                    }
                                    if (itemsNavigationBar.title == "Compendium") {
                                        selectedTabName = "Compendium"
                                    }
                                    if (itemsNavigationBar.title == "Fusion Calc") {
                                        selectedTabName = "Fusion Calculator"
                                    }
                                    if (itemsNavigationBar.title == "100% Guide") {
                                        selectedTabName = "100% Guide"
                                    }
                                },
                                //Nom del item per al menú
                                label = {

                                    Text(text = itemsNavigationBar.title,
                                        color = Color(15, 139, 237))
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
                        //AQUI ACABA EL LOOP DE LOS ITEMS
                    }
                },

                ) {
                //CONTENIDO DENTRO DEL SCAFFOLD
                    paddingValues ->
                paddingValues

                Box(
                    Modifier
                        .padding(top = 64.dp, bottom = 80.dp)
                        .fillMaxSize()
                ) {
                    //Se llama a app navigation porque es el unico que "conoce" la cantidad de pantallas, su estado y tiene la capacidad de navegar
                    if (selectedItemIndex==0) {
                        SocialLinksScreen()
                    }
                    if (selectedItemIndex==1) {
                        CompendiumScreen()
                    }
                    if (selectedItemIndex==2){
                        FusionCalculatorScreen()
                    }
                }
            }
        }
    }
}