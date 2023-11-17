import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
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
import com.example.p3re.R
import com.example.p3re.data.Fonts
import com.example.p3re.data.Shadows
import com.example.p3re.viewmodels.selectedShadow

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun DetailedShadowScreen(shadow: Shadows) {

    Box(modifier = Modifier.fillMaxSize()) {

        //https://stackoverflow.com/questions/68937947/how-to-set-drawable-as-a-background-to-image-in-jetpack-compose
        Image(
            painter = painterResource(R.drawable.prueba1),
            contentDescription = null, // Provide a description if needed
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 70.dp, top = 70.dp),
            //por algun motivo pinta sobre toda la pantalla, no solo en el content del scaffold de la main activty, asi que tengo que poner paddings en top y bottom
            contentPadding = (PaddingValues(top = 20.dp))
        ) {
            //No añade items 1 a 1 como en CompendiumScreen, añade un solo item grande para que pueda usar una LazyColumn y desplazarlo todo, basicamente
            item {
                Image(
                    painter = painterResource(id = R.drawable.cowardly_maya),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .size(30.dp),
                )
                //Spacer de la tabla a la imagen principal
                Spacer(modifier = Modifier.height(16.dp))
                Table(contentCell)
                rowDataShadow(label = "Name: " + shadow.name)
                rowDataShadow(label = "Level: " + shadow.lvl.toString())
                rowDataShadow(label = "Found in: " + shadow.area)
                rowDataShadow(label = "Gem drops: " + shadow.gem)
            }
        }
    }
}



val contentCell = listOf(
    "Slash", "Strike", "Pierce", "Fire", "Ice", "Elec", "Wind", "Light", "Dark", "Almi"
)


//CREA LA TABLA ENTERA USANDO TableCell, que es una funcion composable que declaro abajo que lo que ahce es crear una columna de 2 box, aqui es asigno el valor
@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun Table(contentCell: List<String>) {

    //Accedo a esto gracias al viewModel
    val shadowResists = selectedShadow?.resists

    //Convertir a char array para el metodo de transformar a texto y poder rellenar las celdas con texto
    var charArray: CharArray? = null

    if (shadowResists != null) {
        charArray = shadowResists.toCharArray()
    }

//Esto crea y asigna valores a la tabla entera creando TableCell, que es una Box encima de otra
    LazyRow(
        contentPadding = PaddingValues(16.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(10) { columnIndex ->
            TableCell(valueFirstCell = contentCell[columnIndex], valueSecondCell = changeCharToText(charArray?.get(columnIndex).toString()))
        }
    }

}

//Table cell crea las columnas de la tabla, que son dos lineas de Box son margenes a las que les introduzco diferentes strings
//Table cell es la "maqueta" para crear después en Table
@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun TableCell(valueFirstCell: String, valueSecondCell: String) {
    //Se agrupa en una columna para que las box esten una encima de otra
    Column (Modifier.fillMaxSize()){
        Box(
            modifier = Modifier
                .size(100.dp, 40.dp)
                .border(2.dp, Color.Black)
                .background(Color.Transparent),

            contentAlignment = Alignment.Center
        ) {
            Text(text = valueFirstCell, color=(Color(10, 21, 70, 255)), fontFamily = Fonts.summerFontFamily)
        }
        Box(
            modifier = Modifier
                .size(100.dp, 40.dp)
                .border(2.dp, Color.Black)
                .background(Color.Transparent),
            contentAlignment = Alignment.Center
        ) {
            Text(text = valueSecondCell, color=(Color(10, 21, 70, 255)), fontFamily = Fonts.summerFontFamily)
        }
    }
}




//ESTOS SON LOS VALORES NOMBRE, RAZA, GEMA ETC QUE SE DISPLAYEAN EN LA DETAILED SHADOW SCREEN
@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun rowDataShadow(label: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            //padding para que no se toquen entre los Box
            .background(Color.Transparent)
            .border(border = BorderStroke(width = 1.dp, color = Color.Black))
    ) {
        //Row dentro del contenedor Box que es la etiqueta principal para añadir el cuadrito amarillo y texto alineados en el centro
        Row(
            modifier = Modifier.padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            //Caja de la izquierda
            /*Box(
                modifier = Modifier
                    .size(16.dp)
                    .background(Color(10, 21, 70, 255))
            )*/
            Text(
                text = label.uppercase(),
                color = Color(10, 21, 70, 255),
                fontSize = 18.sp,
                //el padding de arriba y abajo es para que sea mas gorda la row y no de tanto asco
                modifier = Modifier.padding(start = 8.dp, top = 16.dp, bottom = 16.dp),
                fontWeight = FontWeight.Normal,
                fontFamily = Fonts.summerFontFamily,
            )
        }
    }
}
fun changeCharToText(inputString: String):String{

    if(inputString=="w"){
        return "Weak"
    }
    if(inputString=="s"){
        return "Strong"
    }
    if(inputString=="r"){
        return "Resists"
    }
    if(inputString=="d"){
        return "Deflect"
    }
    if(inputString=="d"){
        return "Null"
    }
    if(inputString=="a"){
        return "Abosrbs"
    }
    return "-"
}