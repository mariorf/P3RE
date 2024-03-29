import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.p3re.R
import com.example.p3re.data.Shadow
import com.example.p3re.data.ShadowViewModel
import com.example.p3re.data.selectedShadow
import com.example.p3re.screens.minervaFamily

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun DetailedShadowScreen(shadow: Shadow) {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(15, 139, 237)),
        //por algun motivo pinta sobre toda la pantalla, no solo en el content del scaffold de la main activty, asi que tengo que poner paddings en top y bottom
        contentPadding = (PaddingValues(bottom = 100.dp, top = 100.dp ))
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
            Spacer(modifier = Modifier.height(16.dp))
            Table(contentCell)
            rowDataShadow(label = "Name: " + shadow.name)
            rowDataShadow(label = "Level: " + shadow.lvl.toString())
            rowDataShadow(label = "Found in: " + shadow.area)
            rowDataShadow(label = "Skills: " + shadow.skills)
            rowDataShadow(label = "Gem drops: " + shadow.gem)
        }
    }
}



val contentCell = listOf(
    "Slash",
    "Strike",
    "Pierce",
    "Fire",
    "Ice",
    "Elec",
    "Wind",
    "Light",
    "Dark",
    "Almi"
)

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
                .border(4.dp, Color.Black)
                .background(Color(2, 46, 73)),

            contentAlignment = Alignment.Center
        ) {
            Text(text = valueFirstCell, color=(Color.White), fontFamily = minervaFamily)
        }
        Box(
            modifier = Modifier
                .size(100.dp, 40.dp)
                .border(4.dp, Color.Black)
                .background(Color(2, 46, 73)),
            contentAlignment = Alignment.Center
        ) {
            Text(text = valueSecondCell, color=(Color.White), fontFamily = minervaFamily)
        }
    }
}

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

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun rowDataShadow(label: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            //padding para que no se toquen entre los Box
            .padding(8.dp)
            .background(Color(2, 46, 73))
    ) {
        //Row dentro del contenedor Box que es la etiqueta principal para añadir el cuadrito amarillo y texto alineados en el centro
        Row(
            modifier = Modifier.padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .background(Color(254, 189, 97))
            )
            Text(
                text = label,
                color = Color.White,
                fontSize = 16.sp,
                //el padding de arriba y abajo es para que sea mas gorda la row y no de tanto asco
                modifier = Modifier.padding(start = 8.dp, top = 16.dp, bottom = 16.dp),
                fontWeight = FontWeight.Normal,
                fontFamily = minervaFamily,
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
        return "Deflects"
    }
    if(inputString=="d"){
        return "Null"
    }
    if(inputString=="a"){
        return "Abosrbs"
    }
    return "-"
}