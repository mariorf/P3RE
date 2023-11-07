package com.example.p3re.screens.navigation

//Por que seales class? Para que esten "selladas" aquí y que estas sean las unicas pantallas a las que se pueda navegar, ya que podría haber pantallas (tipo pop ups) a las que no queremos navegar
//Clase para centralizar todas las pantallas
//Para navegar en jetpack compose necesitas la ruta, que es un identificador que se recibirá como parametro (es un tipo string normal que sirve como ID)
sealed class AppScreens(val route: String){

    //Creación de los objetos AppScreens con sus parametros route identificativos
    object CompendiumScreen: AppScreens("compendium_screen")
    object SocialLinksScreen: AppScreens("social_links_screen")

}
