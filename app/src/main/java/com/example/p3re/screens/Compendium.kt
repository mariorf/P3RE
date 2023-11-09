package com.example.p3re.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.room.TypeConverters
import com.example.p3re.R
import com.example.p3re.data.Shadow
import com.example.p3re.data.ShadowDAO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.concurrent.Executors


//RECIBEN COMO PARAMETROS NAV CONTROLLERS LAS FUNCIONES A LAS QUE VAS A TENER QUE PODER NAVEGAR

val json = """
{
  "Acheron Seeker": {
    "name": "Acheron Seeker",
    "area": "Monad Depths 991-999",
    "gem": "-",
    "lvl": 96,
    "race": "Hermit",
    "resists": "---dddd---",
    "skills": ["Strike Attack", "Maragidyne", "Mabufudyne", "Maziodyne", "Magarudyne", "Infuriate", "Tetrakarn"],
    "stats": [478, 235, 47, 73, 49, 90, 36]
  },
  "Adamant Beetle": {
    "name": "Adamant Beetle",
    "area": "Arqa II 41-63",
    "gem": "Aquamarine",
    "lvl": 19,
    "race": "Emperor",
    "resists": "s-n--w----",
    "skills": ["Pierce Attack", "Kill Rush", "Power Charge"],
    "stats": [549, 234, 25, 10, 23, 11, 10]
  },
  "Almighty Hand": {
    "name": "Almighty Hand",
    "area": "Yabbashah II 99-113",
    "gem": "Onyx",
    "lvl": 34,
    "race": "Magician",
    "resists": "----d--nw-",
    "skills": ["Strike Attack", "Mabufula", "Bufula", "Hama", "Mahama"],
    "stats": [238, 136, 16, 38, 19, 19, 17]
  },
  "Amenti Raven": {
    "name": "Amenti Raven",
    "area": "Adamah 215-227",
    "gem": "Ruby",
    "lvl": 65,
    "race": "Hermit",
    "resists": "---r-ws---",
    "skills": ["Pierce Attack", "Evil Smile", "Mamudoon", "Garudyne"],
    "stats": [356, 203, 30, 39, 31, 65, 37]
  },
  "Amorous Snake": {
    "name": "Amorous Snake",
    "area": "Monad Depths 991-999",
    "gem": "-",
    "lvl": 93,
    "race": "Lovers",
    "resists": "sssdw--rw-",
    "skills": ["Pierce Attack", "Agidyne", "Mahama", "Sexy Dance", "Virus Breath", "Re Patra", "Mediarahan"],
    "stats": [470, 230, 60, 71, 49, 47, 59]
  },
  "Angry Table": {
    "name": "Angry Table",
    "area": "Harabah 191-200",
    "gem": "Amethyst",
    "lvl": 61,
    "race": "Magician",
    "resists": "w---n-w---",
    "skills": ["Strike Attack", "Bufudyne", "Mabufula", "Tentarafoo", "Tetrakarn"],
    "stats": [238, 136, 13, 33, 22, 22, 21]
  },
  "Apostate Tower": {
    "name": "Apostate Tower",
    "area": "Harabah 165-179",
    "gem": "Onyx",
    "lvl": 56,
    "race": "Hierophant",
    "resists": "---wsd----",
    "skills": ["Pierce Attack", "Mazionga", "Poison Mist", "Virus Breath", "Elec Boost"],
    "stats": [302, 172, 35, 44, 30, 33, 33]
  },
  "Arcane Turret": {
    "name": "Arcane Turret",
    "area": "Tziah I 115-138",
    "gem": "Diamond",
    "lvl": 46,
    "race": "Chariot",
    "resists": "-nn--w----",
    "skills": ["Strike Attack", "Vile Assault", "Tarukaja"],
    "stats": [1008, 426, 54, 9, 43, 11, 13]
  },
  "Ardent Dancer": {
    "name": "Ardent Dancer",
    "area": "Yabbashah I 73-89",
    "gem": "Amethyst",
    "lvl": 30,
    "race": "Lovers",
    "resists": "-s------w-",
    "skills": ["Pierce Attack", "Double Fangs", "Sexy Dance", "Marin Karin"],
    "stats": [219, 108, 22, 22, 16, 14, 23]
  },
  "Avenger Knight": {
    "name": "Avenger Knight",
    "area": "Yabbashah I 65-89",
    "gem": "Emerald",
    "lvl": 30,
    "race": "Emperor",
    "resists": "n------w--",
    "skills": ["Pierce Attack", "Mazio", "Gigantic Fist", "Provoke", "Cleave", "Tarukaja"],
    "stats": [765, 375, 24, 22, 23, 17, 11]
  },
  "Battle Wheel": {
    "name": "Battle Wheel",
    "area": "Harabah 181-189",
    "gem": "Topaz",
    "lvl": 59,
    "race": "Chariot",
    "resists": "-------n--",
    "skills": ["Strike Attack", "Heat Wave", "Agidyne", "Eerie Sound"],
    "stats": [359, 152, 40, 35, 42, 38, 29]
  },
  "Bestial Wheel": {
    "name": "Bestial Wheel",
    "area": "Arqa I 17-39",
    "gem": "Amethyst",
    "lvl": 15,
    "race": "Chariot",
    "resists": "s-s--ww---",
    "skills": ["Strike Attack", "Assault Dive", "Power Charge"],
    "stats": [399, 183, 16, 10, 16, 10, 10]
  },
  "Bigoted Maya": {
    "name": "Bigoted Maya",
    "area": "Adamah 215-227",
    "gem": "Topaz",
    "lvl": 65,
    "race": "Justice",
    "resists": "---w-s----",
    "skills": ["Slash Attack", "Ziodyne", "Diarama"],
    "stats": [383, 188, 51, 48, 45, 24, 34]
  },
  "Black Raven": {
    "name": "Black Raven",
    "area": "Arqa I 17-24",
    "gem": "Aquamarine",
    "lvl": 9,
    "race": "Hermit",
    "resists": "---n-ws---",
    "skills": ["Pierce Attack", "Agi", "Double Fangs", "Poisma", "Agilao"],
    "stats": [92, 46, 8, 6, 5, 11, 4]
  },
  "Blue Sigil": {
    "name": "Blue Sigil",
    "area": "Tziah I 115-138",
    "gem": "Topaz",
    "lvl": 40,
    "race": "Hierophant",
    "resists": "----d---w-",
    "skills": ["Slash Attack", "Bufudyne", "Mabufula", "Bewilder"],
    "stats": [260, 148, 25, 33, 28, 20, 18]
  },
  "Brave Wheel": {
    "name": "Brave Wheel",
    "area": "Tziah I 123-138",
    "gem": "Sapphire",
    "lvl": 45,
    "race": "Chariot",
    "resists": "---dw-----",
    "skills": ["Strike Attack", "Agidyne", "Kill Rush", "Gigantic Fist", "Rakunda"],
    "stats": [307, 130, 35, 15, 32, 23, 19]
  },
  "Bronze Dice": {
    "name": "Bronze Dice",
    "area": "Arqa II 41-46",
    "gem": "Turquoise",
    "lvl": 18,
    "race": "Fortune",
    "resists": "-n---w----",
    "skills": ["Strike Attack", "Bash", "Kill Rush", "Double Fangs"],
    "stats": [183, 78, 17, 5, 14, 9, 16]
  },
  "Carnal Snake": {
    "name": "Carnal Snake",
    "area": "Adamah 237-261",
    "gem": "Topaz",
    "lvl": 75,
    "race": "Lovers",
    "resists": "----w-n---",
    "skills": ["Pierce Attack", "Evil Touch", "Infuriate", "Mahamaon", "Garudyne", "Life Drain", "Spirit Drain", "Mediarama", "Eerie Sound"],
    "stats": [361, 177, 49, 60, 39, 36, 48]
  },
  "Champion Knight": {
    "name": "Champion Knight",
    "area": "Tziah I 115-138",
    "gem": "Garnet",
    "lvl": 40,
    "race": "Emperor",
    "resists": "sss--dw---",
    "skills": ["Pierce Attack", "Heat Wave", "Sukunda", "Zionga"],
    "stats": [269, 132, 28, 28, 27, 22, 16]
  },
  "Chaos Cyclops": {
    "name": "Chaos Cyclops",
    "area": "Monad Depths 996-999",
    "gem": "-",
    "lvl": 98,
    "race": "Hanged",
    "resists": "----d--nn-",
    "skills": ["Slash Attack", "Bufudyne", "Megidolaon", "Marakukaja", "God's Hand", "Primal Force", "Diarahan", "Infuriate"],
    "stats": [487, 239, 98, 81, 66, 28, 31]
  },
  "Constancy Relic": {
    "name": "Constancy Relic",
    "area": "Tziah I 115-138",
    "gem": "Turquoise",
    "lvl": 39,
    "race": "Empress",
    "resists": "---w--n---",
    "skills": ["Pierce Attack", "Media", "Garudyne", "Rakukaja", "Re Patra"],
    "stats": [252, 143, 21, 31, 32, 15, 19]
  },
  "Conviction Sword": {
    "name": "Conviction Sword",
    "area": "Tziah II 151-163",
    "gem": "Diamond",
    "lvl": 51,
    "race": "Justice",
    "resists": "s-s---w---",
    "skills": ["Slash Attack", "Marakunda", "Mighty Swing", "Counter"],
    "stats": [296, 146, 28, 50, 26, 24, 32]
  },
  "Cowardly Maya": {
    "name": "Cowardly Maya",
    "area": "Thebel 1-15",
    "gem": "-",
    "lvl": 2,
    "race": "Magician",
    "resists": "---w-ww---",
    "skills": ["Slash Attack", "Bufu"],
    "stats": [42, 13, 2, 4, 3, 2, 2]
  },
  "Crazy Twins": {
    "name": "Crazy Twins",
    "area": "Harabah 165-179",
    "gem": "Aquamarine",
    "lvl": 56,
    "race": "Hierophant",
    "resists": "---w-r-nw-",
    "skills": ["Strike Attack", "Ziodyne", "Mazionga", "Mind Charge", "Mahama"],
    "stats": [302, 172, 33, 41, 34, 33, 34]
  },
  "Creation Relic": {
    "name": "Creation Relic",
    "area": "Yabbashah I 65-89",
    "gem": "Amethyst",
    "lvl": 26,
    "race": "Empress",
    "resists": "------rnw-",
    "skills": ["Pierce Attack", "Magarula", "Garula", "Diarama", "Last Resort", "Hama"],
    "stats": [181, 103, 15, 24, 25, 9, 12]
  },
  "Crying Table": {
    "name": "Crying Table",
    "area": "Arqa II 48-63",
    "gem": "Aquamarine",
    "lvl": 21,
    "race": "Magician",
    "resists": "---nw-----",
    "skills": ["Strike Attack", "Maragi", "Agilao", "Dekaja"],
    "stats": [170, 97, 15, 18, 14, 13, 10]
  },
  "Curse Dice": {
    "name": "Curse Dice",
    "area": "Harabah 191-200",
    "gem": "Topaz",
    "lvl": 62,
    "race": "Fortune",
    "resists": "sss---w-n-",
    "skills": ["Slash Attack", "Arrow Rain", "Infuriate"],
    "stats": [342, 168, 19, 46, 24, 39, 44]
  },
  "Dancing Hand": {
    "name": "Dancing Hand",
    "area": "Arqa I 26-39",
    "gem": "Onyx",
    "lvl": 13,
    "race": "Magician",
    "resists": "---wn-----",
    "skills": ["Strike Attack", "Mabufu", "Bufula"],
    "stats": [88, 46, 7, 15, 5, 9, 10]
  },
  "Daring Gigas": {
    "name": "Daring Gigas",
    "area": "Adamah 229-235",
    "gem": "Diamond",
    "lvl": 70,
    "race": "Strength",
    "resists": "sss---w---",
    "skills": ["Strike Attack", "Akasha Arts", "Gigantic Fist"],
    "stats": [409, 174, 42, 36, 54, 44, 41]
  },
  "Dark Eagle": {
    "name": "Dark Eagle",
    "area": "Arqa II 48-63",
    "gem": "Turquoise",
    "lvl": 23,
    "race": "Empress",
    "resists": "-----wn---",
    "skills": ["Slash Attack", "Garula", "Magaru", "Dia", "Dodge Pierce"],
    "stats": [132, 65, 16, 13, 12, 23, 12]
  },
  "Death Castle": {
    "name": "Death Castle",
    "area": "Adamah 215-227",
    "gem": "Emerald",
    "lvl": 68,
    "race": "Emperor",
    "resists": "----ndwrr-",
    "skills": ["Strike Attack", "Bufudyne", "Maziodyne", "Mudoon", "Mamudoon", "Ice Amp"],
    "stats": [1170, 573, 46, 55, 61, 22, 27]
  },
  "Death Dice": {
    "name": "Death Dice",
    "area": "Adamah 215-227",
    "gem": "Garnet",
    "lvl": 66,
    "race": "Fortune",
    "resists": "---nw-rss-",
    "skills": ["Slash Attack", "Hamaon", "Mudoon", "Mahamaon", "Mamudoon"],
    "stats": [379, 186, 26, 53, 31, 45, 30]
  },
  "Death Seeker": {
    "name": "Death Seeker",
    "area": "Tziah II 140-163",
    "gem": "Topaz",
    "lvl": 49,
    "race": "Hermit",
    "resists": "---n---wr-",
    "skills": ["Strike Attack", "Maragion", "Mudo", "Mamudo", "Mudoon"],
    "stats": [277, 158, 19, 41, 21, 55, 18]
  },
  "Death Twins": {
    "name": "Death Twins",
    "area": "Tziah I 123-138",
    "gem": "Opal",
    "lvl": 42,
    "race": "Hierophant",
    "resists": "-w---d-r--",
    "skills": ["Strike Attack", "Zionga", "Mazionga", "Rakunda", "Mind Charge"],
    "stats": [260, 148, 23, 30, 24, 23, 24]
  },
  "Desirous Maya": {
    "name": "Desirous Maya",
    "area": "Harabah 191-200",
    "gem": "Emerald",
    "lvl": 61,
    "race": "Hermit",
    "resists": "---w------",
    "skills": ["Slash Attack", "Spirit Drain", "Poisma", "Matarukaja"],
    "stats": [334, 164, 22, 49, 21, 55, 30]
  },
  "Devious Maya": {
    "name": "Devious Maya",
    "area": "Adamah 237-261",
    "gem": "Sapphire",
    "lvl": 70,
    "race": "Hanged",
    "resists": "----nw----",
    "skills": ["Slash Attack", "Diarama", "Bufula", "Last Resort"],
    "stats": [390, 191, 50, 63, 45, 32, 27]
  },
  "Devoted Cupid": {
    "name": "Devoted Cupid",
    "area": "Yabbashah I 73-89",
    "gem": "Onyx",
    "lvl": 28,
    "race": "Lovers",
    "resists": "----w-n---",
    "skills": ["Pierce Attack", "Holy Arrow", "Pulinpa", "Dia"],
    "stats": [203, 116, 15, 20, 15, 22, 19]
  },
  "Divine Mother": {
    "name": "Divine Mother",
    "area": "Monad Depths 991-999",
    "gem": "-",
    "lvl": 90,
    "race": "Empress",
    "resists": "----wn-rw-",
    "skills": ["Strike Attack", "Mahama", "Mahamaon", "Matarunda", "Infuriate"],
    "stats": [438, 249, 61, 67, 53, 46, 50]
  },
  "Dogmatic Tower": {
    "name": "Dogmatic Tower",
    "area": "Yabbashah I 65-71",
    "gem": "Topaz",
    "lvl": 27,
    "race": "Hierophant",
    "resists": "---n-n-n--",
    "skills": ["Pierce Attack", "Mazio", "Poisma"],
    "stats": [198, 112, 19, 23, 13, 15, 18]
  },
  "Doom Sword": {
    "name": "Doom Sword",
    "area": "Adamah 229-235",
    "gem": "Sapphire",
    "lvl": 69,
    "race": "Justice",
    "resists": "s---wr----",
    "skills": ["Slash Attack", "Mazionga", "Deathbound", "Spirit Drain", "Sukukaja"],
    "stats": [386, 190, 58, 40, 36, 34, 46]
  },
  "Elegant Mother": {
    "name": "Elegant Mother",
    "area": "Tziah I 126-138",
    "gem": "Emerald",
    "lvl": 43,
    "race": "Empress",
    "resists": "----swn-w-",
    "skills": ["Strike Attack", "Media", "Magarula", "Mabufula"],
    "stats": [252, 143, 30, 34, 24, 18, 21]
  },
  "Emperor Beetle": {
    "name": "Emperor Beetle",
    "area": "Adamah 237-261",
    "gem": "Opal",
    "lvl": 73,
    "race": "Emperor",
    "resists": "nwn-------",
    "skills": ["Pierce Attack", "Ziodyne", "Poison Mist", "Marakunda", "Akasha Arts"],
    "stats": [459, 194, 53, 39, 52, 42, 40]
  },
  "Enslaved Beast": {
    "name": "Enslaved Beast",
    "area": "Arqa II 48-63",
    "gem": "Onyx",
    "lvl": 23,
    "race": "Strength",
    "resists": "---w------",
    "skills": ["Strike Attack", "Double Fangs", "Swift Strike", "Counter"],
    "stats": [176, 74, 20, 15, 20, 13, 8]
  },
  "Eternal Eagle": {
    "name": "Eternal Eagle",
    "area": "Adamah 237-261",
    "gem": "Garnet",
    "lvl": 74,
    "race": "Empress",
    "resists": "-----ddrw-",
    "skills": ["Slash Attack", "Garudyne", "Magarula", "Magarudyne", "Masukukaja"],
    "stats": [410, 202, 45, 42, 41, 58, 43]
  },
  "Eternal Sand": {
    "name": "Eternal Sand",
    "area": "Monad Depths 991-999",
    "gem": "-",
    "lvl": 97,
    "race": "Fortune",
    "resists": "ww-nnnd---",
    "skills": ["Slash Attack", "Magarudyne", "Garudyne", "Masukunda", "Wind Break", "Eerie Sound", "Tentarafoo"],
    "stats": [481, 236, 54, 78, 62, 53, 51]
  },
  "Fate Seeker": {
    "name": "Fate Seeker",
    "area": "Harabah 191-200",
    "gem": "Garnet",
    "lvl": 62,
    "race": "Hermit",
    "resists": "--wnnnnwr-",
    "skills": ["Strike Attack", "Mamudo", "Maragion", "Mazionga"],
    "stats": [338, 166, 22, 49, 24, 59, 21]
  },
  "Fierce Cyclops": {
    "name": "Fierce Cyclops",
    "area": "Adamah 237-261",
    "gem": "Pearl",
    "lvl": 78,
    "race": "Hanged",
    "resists": "d--d------",
    "skills": ["Slash Attack", "Last Resort", "Foul Breath", "Agidyne", "Infuriate", "Dekaja", "God's Hand"],
    "stats": [397, 195, 66, 48, 57, 36, 34]
  },
  "Flowing Sand": {
    "name": "Flowing Sand",
    "area": "Tziah II 140-150",
    "gem": "Sapphire",
    "lvl": 48,
    "race": "Fortune",
    "resists": "wwwrrrr---",
    "skills": ["Slash Attack", "Magarula", "Maragion", "Tetrakarn"],
    "stats": [292, 144, 24, 45, 33, 23, 26]
  },
  "Frivolous Maya": {
    "name": "Frivolous Maya",
    "area": "Arqa I 17-24",
    "gem": "Aquamarine",
    "lvl": 11,
    "race": "Empress",
    "resists": "-----sw---",
    "skills": ["Slash Attack", "Zio", "Mazio"],
    "stats": [125, 62, 8, 7, 7, 12, 7]
  },
  "Furious Gigas": {
    "name": "Furious Gigas",
    "area": "Yabbashah II 99-113",
    "gem": "Emerald",
    "lvl": 36,
    "race": "Strength",
    "resists": "-s-s-sww--",
    "skills": ["Strike Attack", "Kill Rush", "Gigantic Fist", "Power Charge"],
    "stats": [246, 105, 23, 16, 31, 24, 21]
  },
  "Glorious Hand": {
    "name": "Glorious Hand",
    "area": "Adamah 215-254",
    "gem": "-",
    "lvl": 1,
    "race": "Magician",
    "resists": "----------",
    "skills": ["Strike Attack"],
    "stats": [100, 100, 1, 1, 1, 99, 99]
  },
  "Golden Beetle": {
    "name": "Golden Beetle",
    "area": "Yabbashah I 73-89",
    "gem": "Turquoise",
    "lvl": 29,
    "race": "Emperor",
    "resists": "----n-w---",
    "skills": ["Pierce Attack", "Sonic Punch", "Zionga"],
    "stats": [217, 92, 18, 16, 27, 18, 15]
  },
  "Gracious Cupid": {
    "name": "Gracious Cupid",
    "area": "Harabah 181-189",
    "gem": "Garnet",
    "lvl": 58,
    "race": "Lovers",
    "resists": "-w--n-----",
    "skills": ["Pierce Attack", "Torrent Shot", "Marin Karin", "Mediarama", "Marakukaja"],
    "stats": [306, 174, 31, 41, 30, 42, 37]
  },
  "Grand Magus": {
    "name": "Grand Magus",
    "area": "Monad Depths 991-999",
    "gem": "-",
    "lvl": 88,
    "race": "Magician",
    "resists": "-n-wr--rr-",
    "skills": ["Strike Attack", "Maragidyne", "Agidyne", "Mind Charge", "Tentarafoo", "Megidolaon"],
    "stats": [432, 246, 42, 85, 51, 52, 41]
  },
  "Grave Beetle": {
    "name": "Grave Beetle",
    "area": "Thebel 6-15",
    "gem": "-",
    "lvl": 7,
    "race": "Emperor",
    "resists": "-----sw---",
    "skills": ["Pierce Attack", "Bash"],
    "stats": [163, 76, 11, 2, 9, 4, 2]
  },
  "Green Sigil": {
    "name": "Green Sigil",
    "area": "Adamah 215-227",
    "gem": "Garnet",
    "lvl": 67,
    "race": "Hierophant",
    "resists": "---w-d-r--",
    "skills": ["Slash Attack", "Sexy Dance", "Maragion", "Myriad Arrows", "Tentarafoo"],
    "stats": [343, 195, 41, 57, 44, 36, 30]
  },
  "Grieving Tiara": {
    "name": "Grieving Tiara",
    "area": "Arqa I 17-24",
    "gem": "Amethyst",
    "lvl": 9,
    "race": "Priestess",
    "resists": "---nw-----",
    "skills": ["Strike Attack", "Agi", "Maragi", "Re Patra"],
    "stats": [111, 64, 5, 11, 2, 9, 7]
  },
  "Growth Relic": {
    "name": "Growth Relic",
    "area": "Tziah II 151-163",
    "gem": "Amethyst",
    "lvl": 50,
    "race": "Empress",
    "resists": "-w----d---",
    "skills": ["Pierce Attack", "Magarula", "Diarama", "Sukukaja", "Wind Boost"],
    "stats": [294, 167, 29, 39, 40, 23, 26]
  },
  "Hakurou Musha": {
    "name": "Hakurou Musha",
    "area": "Tziah II 151-163",
    "gem": "Opal",
    "lvl": 54,
    "race": "Strength",
    "resists": "-------ww-",
    "skills": ["Slash Attack", "Zan-ei", "Power Charge", "Evade Slash", "Evade Strike", "Evade Pierce"],
    "stats": [317, 134, 42, 22, 38, 43, 24]
  },
  "Hallowed Turret": {
    "name": "Hallowed Turret",
    "area": "Monad Depths 991-999",
    "gem": "-",
    "lvl": 94,
    "race": "Chariot",
    "resists": "rrr--w-ss-",
    "skills": ["Strike Attack", "Primal Force", "Vile Assault", "Power Charge", "Marakunda"],
    "stats": [520, 220, 95, 26, 94, 30, 44]
  },
  "Harmony Giant": {
    "name": "Harmony Giant",
    "area": "Adamah 237-261",
    "gem": "Topaz",
    "lvl": 77,
    "race": "Justice",
    "resists": "-nr-wrr---",
    "skills": ["Slash Attack", "Magarudyne", "Tempest Slash", "Megido"],
    "stats": [390, 191, 69, 59, 55, 26, 33]
  },
  "Haughty Maya": {
    "name": "Haughty Maya",
    "area": "Arqa II 41-46",
    "gem": "Onyx",
    "lvl": 20,
    "race": "Emperor",
    "resists": "-----sw---",
    "skills": ["Slash Attack", "Zio"],
    "stats": [132, 65, 21, 9, 18, 12, 7]
  },
  "Heat Balance": {
    "name": "Heat Balance",
    "area": "Arqa I 17-24",
    "gem": "Onyx",
    "lvl": 10,
    "race": "Justice",
    "resists": "---ssww---",
    "skills": ["Pierce Attack", "Agi", "Bufu", "Maragi", "Mabufu"],
    "stats": [112, 50, 7, 13, 6, 6, 5]
  },
  "Hell Knight": {
    "name": "Hell Knight",
    "area": "Harabah 181-189",
    "gem": "Amethyst",
    "lvl": 60,
    "race": "Emperor",
    "resists": "n-n--rw---",
    "skills": ["Pierce Attack", "Vile Assault", "Maziodyne", "Power Charge", "Rakunda"],
    "stats": [314, 154, 40, 44, 39, 33, 31]
  },
  "Ice Raven": {
    "name": "Ice Raven",
    "area": "Yabbashah II 90-97",
    "gem": "Garnet",
    "lvl": 33,
    "race": "Hermit",
    "resists": "---ws-s---",
    "skills": ["Pierce Attack", "Kill Rush", "Mamudo", "Magaru"],
    "stats": [230, 113, 18, 20, 19, 33, 16]
  },
  "Ill-fated Maya": {
    "name": "Ill-fated Maya",
    "area": "Tziah I 115-138",
    "gem": "Emerald",
    "lvl": 39,
    "race": "Chariot",
    "resists": "-s---ww---",
    "skills": ["Slash Attack", "Assault Dive", "Swift Strike"],
    "stats": [279, 137, 30, 16, 31, 19, 22]
  },
  "Immoral Snake": {
    "name": "Immoral Snake",
    "area": "Harabah 181-189",
    "gem": "Opal",
    "lvl": 59,
    "race": "Lovers",
    "resists": "--w---nww-",
    "skills": ["Pierce Attack", "Tentarafoo", "Foul Breath", "Marin Karin", "Sexy Dance"],
    "stats": [326, 160, 43, 50, 29, 25, 37]
  },
  "Imprudent Maya": {
    "name": "Imprudent Maya",
    "area": "Harabah 165-179",
    "gem": "Pearl",
    "lvl": 55,
    "race": "Fortune",
    "resists": "---nww----",
    "skills": ["Slash Attack", "Agilao", "Agidyne", "Rakunda", "Sukunda", "Tarunda"],
    "stats": [338, 166, 34, 34, 34, 34, 34]
  },
  "Indolent Maya": {
    "name": "Indolent Maya",
    "area": "Yabbashah I 65-89",
    "gem": "Garnet",
    "lvl": 26,
    "race": "Hierophant",
    "resists": "--w--s----",
    "skills": ["Slash Attack", "Mazio", "Sukunda"],
    "stats": [202, 99, 15, 23, 16, 15, 16]
  },
  "Infinite Sand": {
    "name": "Infinite Sand",
    "area": "Adamah 229-235",
    "gem": "Opal",
    "lvl": 71,
    "race": "Fortune",
    "resists": "---nw-r---",
    "skills": ["Slash Attack", "Megido", "Agidyne", "Maragidyne", "Masukukaja", "Arrow Rain"],
    "stats": [383, 188, 37, 61, 45, 36, 41]
  },
  "Insidious Maya": {
    "name": "Insidious Maya",
    "area": "Yabbashah II 90-113",
    "gem": "Amethyst",
    "lvl": 32,
    "race": "Lovers",
    "resists": "----w-s---",
    "skills": ["Slash Attack", "Eerie Sound", "Life Drain", "Diarama"],
    "stats": [208, 102, 18, 22, 19, 19, 25]
  },
  "Iron Dice": {
    "name": "Iron Dice",
    "area": "Harabah 202-213",
    "gem": "Opal",
    "lvl": 64,
    "race": "Fortune",
    "resists": "sss--w----",
    "skills": ["Strike Attack", "Swift Strike", "Kill Rush", "Deathbound"],
    "stats": [259, 110, 24, 10, 23, 17, 26]
  },
  "Jealous Cupid": {
    "name": "Jealous Cupid",
    "area": "Arqa II 41-46",
    "gem": "Amethyst",
    "lvl": 20,
    "race": "Lovers",
    "resists": "----w-n---",
    "skills": ["Pierce Attack", "Rakukaja", "Pulinpa", "Holy Arrow", "Dia", "Marin Karin"],
    "stats": [139, 79, 10, 15, 11, 16, 15]
  },
  "Jotun of Blood": {
    "name": "Jotun of Blood",
    "area": "Harabah 191-200",
    "gem": "Ruby",
    "lvl": 66,
    "race": "Hanged",
    "resists": "--------w-",
    "skills": ["Strike Attack", "Deathbound", "Infuriate"],
    "stats": [910, 564, 65, 26, 54, 18, 15]
  },
  "Jotun of Evil": {
    "name": "Jotun of Evil",
    "area": "Adamah 237-261",
    "gem": "Ruby",
    "lvl": 77,
    "race": "Hanged",
    "resists": "wwwrrrrrr-",
    "skills": ["Strike Attack", "Hamaon", "Bufudyne", "Mabufudyne", "Provoke", "Infuriate", "Evade Slash", "Evade Strike", "Evade Pierce"],
    "stats": [1260, 618, 65, 80, 49, 23, 21]
  },
  "Jotun of Power": {
    "name": "Jotun of Power",
    "area": "Yabbashah II 99-113",
    "gem": "Sapphire",
    "lvl": 38,
    "race": "Hanged",
    "resists": "----d--s--",
    "skills": ["Strike Attack", "Bufudyne", "Diarama", "Bufula", "Hama", "Last Resort"],
    "stats": [807, 396, 40, 19, 34, 13, 15]
  },
  "Judgement Sword": {
    "name": "Judgement Sword",
    "area": "Harabah 202-213",
    "gem": "Topaz",
    "lvl": 64,
    "race": "Justice",
    "resists": "s------nw-",
    "skills": ["Slash Attack", "Heat Wave", "Marakunda", "Tarukaja"],
    "stats": [346, 169, 49, 32, 28, 26, 34]
  },
  "Jupiter Eagle": {
    "name": "Jupiter Eagle",
    "area": "Yabbashah I 73-89",
    "gem": "Aquamarine",
    "lvl": 29,
    "race": "Empress",
    "resists": "-----wd---",
    "skills": ["Slash Attack", "Garula", "Masukunda", "Sukukaja", "Magarula", "Media"],
    "stats": [191, 94, 19, 16, 15, 26, 16]
  },
  "Justice Sword": {
    "name": "Justice Sword",
    "area": "Yabbashah II 90-113",
    "gem": "Onyx",
    "lvl": 32,
    "race": "Justice",
    "resists": "-----sw---",
    "skills": ["Slash Attack", "Mighty Swing", "Zionga"],
    "stats": [240, 118, 34, 18, 15, 14, 22]
  },
  "Kaiden Musha": {
    "name": "Kaiden Musha",
    "area": "Harabah 165-179",
    "gem": "Diamond",
    "lvl": 57,
    "race": "Strength",
    "resists": "sss----w--",
    "skills": ["Slash Attack", "Blade of Fury", "Power Charge", "Evade Slash", "Evade Strike", "Evade Pierce"],
    "stats": [363, 154, 47, 24, 40, 45, 22]
  },
  "Killer Drive": {
    "name": "Killer Drive",
    "area": "Tziah I 115-138",
    "gem": "Opal",
    "lvl": 41,
    "race": "Chariot",
    "resists": "-r-----w--",
    "skills": ["Slash Attack", "Gigantic Fist", "Power Charge", "Evil Smile"],
    "stats": [311, 132, 38, 10, 36, 31, 12]
  },
  "Killer Twins": {
    "name": "Killer Twins",
    "area": "Yabbashah I 65-89",
    "gem": "Opal",
    "lvl": 26,
    "race": "Hierophant",
    "resists": "---w-n----",
    "skills": ["Strike Attack", "Zionga", "Mind Charge"],
    "stats": [192, 109, 15, 21, 16, 17, 16]
  },
  "Killing Hand": {
    "name": "Killing Hand",
    "area": "Arqa II 48-63",
    "gem": "Onyx",
    "lvl": 22,
    "race": "Magician",
    "resists": "---wn---s-",
    "skills": ["Strike Attack", "Bufula", "Mabufu", "Mudo", "Re Patra"],
    "stats": [185, 91, 12, 23, 11, 14, 13]
  },
  "King Castle": {
    "name": "King Castle",
    "area": "Monad Depths 991-999",
    "gem": "-",
    "lvl": 92,
    "race": "Emperor",
    "resists": "-r---dwss-",
    "skills": ["Strike Attack", "Ziodyne", "Maziodyne", "God's Hand", "Poison Mist", "Power Charge", "Elec Break"],
    "stats": [464, 228, 71, 69, 81, 27, 32]
  },
  "Laughing Table": {
    "name": "Laughing Table",
    "area": "Arqa I 26-39",
    "gem": "Aquamarine",
    "lvl": 11,
    "race": "Magician",
    "resists": "--swn-----",
    "skills": ["Strike Attack", "Bufu", "Mabufu", "Bufula", "Pulinpa"],
    "stats": [105, 71, 10, 13, 9, 5, 3]
  },
  "Liberating Idol": {
    "name": "Liberating Idol",
    "area": "Tziah II 140-163",
    "gem": "Aquamarine",
    "lvl": 47,
    "race": "Priestess",
    "resists": "---nw-r---",
    "skills": ["Pierce Attack", "Garudyne", "Evil Touch", "Mabufula", "Ghastly Wail", "Dodge Ice"],
    "stats": [290, 165, 25, 46, 23, 24, 30]
  },
  "Light Balance": {
    "name": "Light Balance",
    "area": "Adamah 229-235",
    "gem": "Diamond",
    "lvl": 68,
    "race": "Justice",
    "resists": "s----wsnn-",
    "skills": ["Pierce Attack", "Magarula", "Mahama", "Mamudo", "Megido"],
    "stats": [386, 190, 40, 50, 39, 40, 42]
  },
  "Lustful Snake": {
    "name": "Lustful Snake",
    "area": "Yabbashah I 65-89",
    "gem": "Aquamarine",
    "lvl": 27,
    "race": "Lovers",
    "resists": "----w-d---",
    "skills": ["Pierce Attack", "Marin Karin", "Media", "Hama"],
    "stats": [214, 105, 19, 24, 12, 10, 23]
  },
  "Luxury Hand": {
    "name": "Luxury Hand",
    "area": "Harabah 165-214",
    "gem": "-",
    "lvl": 1,
    "race": "Magician",
    "resists": "----------",
    "skills": ["Strike Attack"],
    "stats": [100, 100, 1, 1, 1, 99, 99]
  },
  "Mach Wheel": {
    "name": "Mach Wheel",
    "area": "Yabbashah I 65-89",
    "gem": "Turquoise",
    "lvl": 28,
    "race": "Chariot",
    "resists": "n-n--ww---",
    "skills": ["Strike Attack", "Double Fangs", "Tarukaja", "Power Charge"],
    "stats": [241, 102, 28, 10, 26, 14, 13]
  },
  "Mad Cyclops": {
    "name": "Mad Cyclops",
    "area": "Harabah 202-213",
    "gem": "Pearl",
    "lvl": 66,
    "race": "Hanged",
    "resists": "---sn--w--",
    "skills": ["Slash Attack", "Agidyne", "Bufudyne", "Fire Boost", "Ice Boost"],
    "stats": [349, 172, 59, 36, 44, 24, 25]
  },
  "Magic Hand": {
    "name": "Magic Hand",
    "area": "Thebel 6-15",
    "gem": "-",
    "lvl": 4,
    "race": "Magician",
    "resists": "---ws-----",
    "skills": ["Strike Attack", "Bufu", "Mabufu"],
    "stats": [52, 24, 3, 7, 4, 3, 2]
  },
  "Magical Magus": {
    "name": "Magical Magus",
    "area": "Tziah I 123-138",
    "gem": "Onyx",
    "lvl": 43,
    "race": "Magician",
    "resists": "---wn-----",
    "skills": ["Strike Attack", "Mabufula", "Bufudyne"],
    "stats": [242, 138, 27, 45, 22, 20, 22]
  },
  "Maniacal Book": {
    "name": "Maniacal Book",
    "area": "Arqa II 48-63",
    "gem": "Amethyst",
    "lvl": 21,
    "race": "Priestess",
    "resists": "----d--nw-",
    "skills": ["Pierce Attack", "Bufula", "Marin Karin", "Spirit Drain"],
    "stats": [119, 68, 11, 18, 13, 12, 16]
  },
  "Merciless Maya": {
    "name": "Merciless Maya",
    "area": "Thebel 1-15",
    "gem": "-",
    "lvl": 3,
    "race": "Priestess",
    "resists": "---swww---",
    "skills": ["Slash Attack", "Agi"],
    "stats": [50, 21, 4, 4, 3, 3, 2]
  },
  "Mighty Beast": {
    "name": "Mighty Beast",
    "area": "Harabah 165-179",
    "gem": "Ruby",
    "lvl": 57,
    "race": "Strength",
    "resists": "-------wr-",
    "skills": ["Strike Attack", "Mudoon", "Gigantic Fist", "Tarukaja"],
    "stats": [367, 456, 45, 30, 41, 33, 29]
  },
  "Mighty Cyclops": {
    "name": "Mighty Cyclops",
    "area": "Tziah II 151-163",
    "gem": "Opal",
    "lvl": 56,
    "race": "Hanged",
    "resists": "---wd-----",
    "skills": ["Slash Attack", "Maragion", "Magarula", "Mabufula", "Mazionga", "Fire Boost", "Ice Boost", "Elec Boost", "Wind Boost"],
    "stats": [301, 148, 51, 34, 40, 24, 26]
  },
  "Mind Dice": {
    "name": "Mind Dice",
    "area": "Yabbashah II 90-113",
    "gem": "Topaz",
    "lvl": 33,
    "race": "Fortune",
    "resists": "---ssws---",
    "skills": ["Slash Attack", "Pulinpa", "Marin Karin", "Tentarafoo", "Sexy Dance"],
    "stats": [223, 127, 7, 33, 11, 26, 29]
  },
  "Minotaur I": {
    "name": "Minotaur I",
    "area": "Adamah 245-261",
    "gem": "Diamond",
    "lvl": 74,
    "race": "Hanged",
    "resists": "sssw---rw-",
    "skills": ["Pierce Attack", "Primal Force", "Tarukaja", "High Counter", "Evade Fire"],
    "stats": [1230, 606, 62, 66, 52, 36, 13]
  },
  "Minotaur II": {
    "name": "Minotaur II",
    "area": "Adamah 229-235",
    "gem": "Ruby",
    "lvl": 71,
    "race": "Hanged",
    "resists": "sss-n---w-",
    "skills": ["Pierce Attack", "Myriad Arrows", "Last Resort", "Mediarama", "Bufudyne", "Matarukaja"],
    "stats": [1200, 588, 56, 61, 50, 34, 19]
  },
  "Minotaur III": {
    "name": "Minotaur III",
    "area": "Tziah II 151-163",
    "gem": "Sapphire",
    "lvl": 55,
    "race": "Hanged",
    "resists": "-n--r-----",
    "skills": ["Pierce Attack", "Bufudyne", "Mabufula", "Gigantic Fist", "Regenerate 2"],
    "stats": [1026, 504, 42, 51, 38, 27, 14]
  },
  "Minotaur IV": {
    "name": "Minotaur IV",
    "area": "Yabbashah II 99-113",
    "gem": "Diamond",
    "lvl": 37,
    "race": "Hanged",
    "resists": "---ws-----",
    "skills": ["Pierce Attack", "Mabufula", "Evil Smile", "Diarama", "Tarukaja", "Fear Boost"],
    "stats": [792, 420, 30, 33, 27, 14, 14]
  },
  "Muttering Tiara": {
    "name": "Muttering Tiara",
    "area": "Thebel 1-15",
    "gem": "-",
    "lvl": 3,
    "race": "Priestess",
    "resists": "---sw-w---",
    "skills": ["Strike Attack", "Agi", "Dia"],
    "stats": [46, 24, 3, 5, 3, 2, 3]
  },
  "Nemean Beast": {
    "name": "Nemean Beast",
    "area": "Adamah 237-261",
    "gem": "Diamond",
    "lvl": 76,
    "race": "Strength",
    "resists": "---w----w-",
    "skills": ["Strike Attack", "God's Hand", "Power Charge", "Provoke"],
    "stats": [409, 174, 60, 41, 57, 40, 37]
  },
  "Noble Seeker": {
    "name": "Noble Seeker",
    "area": "Adamah 245-261",
    "gem": "Ruby",
    "lvl": 76,
    "race": "Hermit",
    "resists": "---r--wrr-",
    "skills": ["Strike Attack", "Mudoon", "Agidyne", "Provoke", "Maragidyne"],
    "stats": [375, 184, 32, 67, 34, 70, 32]
  },
  "Obsessed Cupid": {
    "name": "Obsessed Cupid",
    "area": "Thebel 6-15",
    "gem": "-",
    "lvl": 5,
    "race": "Lovers",
    "resists": "----w-s---",
    "skills": ["Pierce Attack", "Garu", "Dia"],
    "stats": [51, 32, 2, 5, 3, 7, 5]
  },
  "Onnen Musha": {
    "name": "Onnen Musha",
    "area": "Adamah 215-227",
    "gem": "Sapphire",
    "lvl": 70,
    "race": "Strength",
    "resists": "---w------",
    "skills": ["Slash Attack", "Tempest Slash", "Power Charge", "Evade Slash", "Evade Strike", "Evade Pierce"],
    "stats": [405, 172, 56, 32, 48, 55, 26]
  },
  "Opulent Hand": {
    "name": "Opulent Hand",
    "area": "Tziah 115-164",
    "gem": "-",
    "lvl": 1,
    "race": "Magician",
    "resists": "----------",
    "skills": ["Strike Attack"],
    "stats": [100, 100, 1, 1, 1, 99, 99]
  },
  "Order Giant": {
    "name": "Order Giant",
    "area": "Tziah II 140-163",
    "gem": "Emerald",
    "lvl": 48,
    "race": "Justice",
    "resists": "sss-wr----",
    "skills": ["Slash Attack", "Heat Wave", "Tarukaja"],
    "stats": [296, 146, 45, 43, 39, 9, 15]
  },
  "Perpetual Sand": {
    "name": "Perpetual Sand",
    "area": "Harabah 202-213",
    "gem": "Sapphire",
    "lvl": 65,
    "race": "Fortune",
    "resists": "w-wnnnn---",
    "skills": ["Slash Attack", "Magarula", "Maragion", "Makarakarn"],
    "stats": [342, 185, 28, 50, 37, 27, 30]
  },
  "Phantom Lord": {
    "name": "Phantom Lord",
    "area": "Yabbashah II 99-113",
    "gem": "Opal",
    "lvl": 36,
    "race": "Hermit",
    "resists": "---n---wr-",
    "skills": ["Slash Attack", "Mamudo", "Agilao", "Spirit Drain", "Evil Smile"],
    "stats": [219, 124, 24, 29, 21, 24, 17]
  },
  "Phantom Mage": {
    "name": "Phantom Mage",
    "area": "Arqa I 17-24",
    "gem": "Amethyst",
    "lvl": 12,
    "race": "Hermit",
    "resists": "---s---wn-",
    "skills": ["Slash Attack", "Agi", "Maragi", "Mudo"],
    "stats": [121, 64, 9, 14, 8, 7, 5]
  },
  "Phantom Master": {
    "name": "Phantom Master",
    "area": "Arqa II 41-46",
    "gem": "Amethyst",
    "lvl": 17,
    "race": "Hermit",
    "resists": "------w---",
    "skills": ["Slash Attack", "Mudo", "Poisma", "Agilao"],
    "stats": [158, 90, 12, 17, 10, 12, 7]
  },
  "Pistil Mother": {
    "name": "Pistil Mother",
    "area": "Harabah 191-200",
    "gem": "Onyx",
    "lvl": 63,
    "race": "Empress",
    "resists": "----rwr---",
    "skills": ["Strike Attack", "Garudyne", "Bufudyne", "Diarahan"],
    "stats": [339, 193, 43, 46, 35, 28, 32]
  },
  "Platinum Dice": {
    "name": "Platinum Dice",
    "area": "Adamah 237-261",
    "gem": "Pearl",
    "lvl": 73,
    "race": "Fortune",
    "resists": "sssnnwn---",
    "skills": ["Strike Attack", "Tempest Slash", "Akasha Arts", "God's Hand"],
    "stats": [439, 216, 58, 28, 48, 40, 52]
  },
  "Power Castle": {
    "name": "Power Castle",
    "area": "Harabah 181-189",
    "gem": "Turquoise",
    "lvl": 61,
    "race": "Emperor",
    "resists": "---s-nw---",
    "skills": ["Strike Attack", "Maragion", "Ziodyne", "Deathbound"],
    "stats": [314, 15, 41, 51, 57, 18, 23]
  },
  "Prime Magus": {
    "name": "Prime Magus",
    "area": "Harabah 202-213",
    "gem": "Turquoise",
    "lvl": 65,
    "race": "Magician",
    "resists": "---wd-----",
    "skills": ["Strike Attack", "Megido", "Bufudyne", "Mind Charge", "Mamudo"],
    "stats": [335, 191, 35, 64, 25, 22, 35]
  },
  "Red Sigil": {
    "name": "Red Sigil",
    "area": "Harabah 181-189",
    "gem": "Emerald",
    "lvl": 61,
    "race": "Hierophant",
    "resists": "---dw--n--",
    "skills": ["Slash Attack", "Hamaon", "Agidyne", "Maragion", "Dodge Ice"],
    "stats": [306, 174, 38, 49, 41, 32, 30]
  },
  "Regal Mother": {
    "name": "Regal Mother",
    "area": "Tziah II 151-163",
    "gem": "Turquoise",
    "lvl": 53,
    "race": "Empress",
    "resists": "----nwdnw-",
    "skills": ["Strike Attack", "Mediarama", "Magarula", "Mabufula", "Rakukaja", "Wind Boost"],
    "stats": [294, 167, 30, 34, 24, 18, 21]
  },
  "Royal Dancer": {
    "name": "Royal Dancer",
    "area": "Adamah 229-235",
    "gem": "Opal",
    "lvl": 69,
    "race": "Lovers",
    "resists": "------rnn-",
    "skills": ["Pierce Attack", "Myriad Arrows", "Sexy Dance", "Tentarafoo", "Maragion"],
    "stats": [364, 179, 45, 47, 39, 35, 48]
  },
  "Ruinous Idol": {
    "name": "Ruinous Idol",
    "area": "Harabah 165-189",
    "gem": "Aquamarine",
    "lvl": 63,
    "race": "Priestess",
    "resists": "---dw--wr-",
    "skills": ["Pierce Attack", "Mudo", "Mamudo", "Mudoon", "Virus Breath"],
    "stats": [335, 191, 33, 50, 30, 31, 37]
  },
  "Scarlet Turret": {
    "name": "Scarlet Turret",
    "area": "Harabah 165-179",
    "gem": "Sapphire",
    "lvl": 58,
    "race": "Chariot",
    "resists": "rn-s-w----",
    "skills": ["Strike Attack", "Arrow Rain", "Matarukaja", "Maragion", "Agilao"],
    "stats": [363, 154, 67, 16, 56, 18, 24]
  },
  "Shouting Tiara": {
    "name": "Shouting Tiara",
    "area": "Yabbashah II 102-113",
    "gem": "Aquamarine",
    "lvl": 35,
    "race": "Priestess",
    "resists": "---dw--nw-",
    "skills": ["Strike Attack", "Maragi", "Agilao", "Mahama", "Maragion", "Media"],
    "stats": [242, 138, 19, 31, 19, 22, 21]
  },
  "Silent Book": {
    "name": "Silent Book",
    "area": "Yabbashah I 65-89",
    "gem": "Onyx",
    "lvl": 27,
    "race": "Priestess",
    "resists": "---wd-s---",
    "skills": ["Pierce Attack", "Mamudo", "Poisma", "Foul Breath"],
    "stats": [176, 100, 14, 22, 15, 16, 21]
  },
  "Silver Dice": {
    "name": "Silver Dice",
    "area": "Adamah 229-235",
    "gem": "Pearl",
    "lvl": 67,
    "race": "Fortune",
    "resists": "nnn----ww-",
    "skills": ["Strike Attack", "Mighty Swing", "Gigantic Fist", "Deathbound"],
    "stats": [379, 186, 50, 26, 44, 38, 50]
  },
  "Sky Balance": {
    "name": "Sky Balance",
    "area": "Arqa II 41-46",
    "gem": "Onyx",
    "lvl": 17,
    "race": "Justice",
    "resists": "---wwrr---",
    "skills": ["Pierce Attack", "Zionga", "Garula"],
    "stats": [172, 85, 9, 19, 9, 11, 10]
  },
  "Slaughter Drive": {
    "name": "Slaughter Drive",
    "area": "Adamah 215-227",
    "gem": "Topaz",
    "lvl": 66,
    "race": "Chariot",
    "resists": "-----w----",
    "skills": ["Slash Attack", "Deathbound", "Power Charge", "Matarukaja", "Tetrakarn"],
    "stats": [400, 170, 52, 24, 50, 54, 25]
  },
  "Solid Castle": {
    "name": "Solid Castle",
    "area": "Tziah II 140-150",
    "gem": "Emerald",
    "lvl": 49,
    "race": "Emperor",
    "resists": "-----nw---",
    "skills": ["Strike Attack", "Agilao", "Zionga", "Poisma"],
    "stats": [269, 132, 35, 43, 49, 11, 16]
  },
  "Soul Dancer": {
    "name": "Soul Dancer",
    "area": "Arqa I 17-39",
    "gem": "Aquamarine",
    "lvl": 11,
    "race": "Lovers",
    "resists": "----w-n---",
    "skills": ["Pierce Attack", "Dia", "Marin Karin", "Double Fangs"],
    "stats": [146, 72, 12, 11, 4, 3, 10]
  },
  "Spurious Book": {
    "name": "Spurious Book",
    "area": "Arqa I 26-39",
    "gem": "Onyx",
    "lvl": 15,
    "race": "Priestess",
    "resists": "-----wn---",
    "skills": ["Pierce Attack", "Garu", "Magaru"],
    "stats": [115, 59, 10, 18, 7, 8, 9]
  },
  "Stasis Giant": {
    "name": "Stasis Giant",
    "area": "Harabah 202-213",
    "gem": "Garnet",
    "lvl": 66,
    "race": "Justice",
    "resists": "ss--wnn---",
    "skills": ["Slash Attack", "Gigantic Fist", "Rakukaja", "High Counter"],
    "stats": [346, 169, 50, 47, 43, 14, 21]
  },
  "Steel Gigas": {
    "name": "Steel Gigas",
    "area": "Arqa II 41-46",
    "gem": "Turquoise",
    "lvl": 19,
    "race": "Strength",
    "resists": "-s---w-w--",
    "skills": ["Strike Attack", "Sonic Punch", "Provoke", "Revolution", "Counter"],
    "stats": [202, 81, 14, 7, 16, 16, 11]
  },
  "Supreme Hand": {
    "name": "Supreme Hand",
    "area": "Yabbashah 65-114",
    "gem": "-",
    "lvl": 1,
    "race": "Magician",
    "resists": "----------",
    "skills": ["Strike Attack"],
    "stats": [100, 100, 1, 1, 1, 99, 99]
  },
  "Tenjin Musha": {
    "name": "Tenjin Musha",
    "area": "Monad Depths 991-999",
    "gem": "-",
    "lvl": 95,
    "race": "Strength",
    "resists": "-----wd---",
    "skills": ["Slash Attack", "Vorpal Blade", "Myriad Arrows", "Power Charge", "Matarukaja", "Evade Slash", "Evade Strike", "Evade Pierce"],
    "stats": [524, 221, 98, 37, 62, 63, 32]
  },
  "Trance Twins": {
    "name": "Trance Twins",
    "area": "Thebel 11-15",
    "gem": "-",
    "lvl": 6,
    "race": "Hierophant",
    "resists": "---wws----",
    "skills": ["Strike Attack", "Zio", "Spirit Drain", "Mazio"],
    "stats": [62, 36, 4, 8, 5, 4, 4]
  },
  "Tranquil Idol": {
    "name": "Tranquil Idol",
    "area": "Tziah I 115-138",
    "gem": "Amethyst",
    "lvl": 41,
    "race": "Priestess",
    "resists": "---n---rw-",
    "skills": ["Pierce Attack", "Twin Shot", "Marakukaja", "Hamaon"],
    "stats": [247, 140, 19, 37, 17, 18, 24]
  },
  "Treasure Hand": {
    "name": "Treasure Hand",
    "area": "Arqa 17-64",
    "gem": "-",
    "lvl": 1,
    "race": "Magician",
    "resists": "----------",
    "skills": ["Strike Attack"],
    "stats": [100, 100, 1, 1, 1, 99, 99]
  },
  "Vehement Idol": {
    "name": "Vehement Idol",
    "area": "Monad Depths 991-999",
    "gem": "-",
    "lvl": 89,
    "race": "Priestess",
    "resists": "--rr--nwr-",
    "skills": ["Pierce Attack", "Bufudyne", "Mabufudyne", "Mudoon", "Mamudoon"],
    "stats": [435, 247, 52, 83, 45, 44, 50]
  },
  "Venus Eagle": {
    "name": "Venus Eagle",
    "area": "Arqa I 26-39",
    "gem": "Amethyst",
    "lvl": 14,
    "race": "Empress",
    "resists": "---sw-s---",
    "skills": ["Slash Attack", "Magaru", "Sukukaja", "Garula"],
    "stats": [100, 46, 12, 8, 7, 15, 7]
  },
  "Vicious Raven": {
    "name": "Vicious Raven",
    "area": "Arqa I 26-39",
    "gem": "Aquamarine",
    "lvl": 18,
    "race": "Hermit",
    "resists": "---dw-s---",
    "skills": ["Pierce Attack", "Garula", "Double Fangs"],
    "stats": [160, 78, 12, 11, 9, 19, 10]
  },
  "Visceral Maya": {
    "name": "Visceral Maya",
    "area": "Tziah II 140-163",
    "gem": "Garnet",
    "lvl": 47,
    "race": "Strength",
    "resists": "nnnwwww---",
    "skills": ["Slash Attack", "Torrent Shot", "Tarukaja"],
    "stats": [288, 141, 36, 19, 34, 23, 36]
  },
  "Void Giant": {
    "name": "Void Giant",
    "area": "Monad Depths 991-999",
    "gem": "-",
    "lvl": 98,
    "race": "Justice",
    "resists": "ddd----rr-",
    "skills": ["Slash Attack", "Mahamaon", "Hamaon", "Mamudoon", "Mudoon", "Myriad Arrows", "Deathbound"],
    "stats": [484, 237, 90, 76, 72, 28, 35]
  },
  "Wealth Hand": {
    "name": "Wealth Hand",
    "area": "Thebel 2-16",
    "gem": "-",
    "lvl": 1,
    "race": "Magician",
    "resists": "----------",
    "skills": ["Strike Attack"],
    "stats": [100, 100, 1, 1, 1, 99, 99]
  },
  "White Sigil": {
    "name": "White Sigil",
    "area": "Monad Depths 991-999",
    "gem": "-",
    "lvl": 92,
    "race": "Hierophant",
    "resists": "r--wd--r--",
    "skills": ["Slash Attack", "Megidolaon", "Mabufudyne", "Mamudoon", "Mind Charge", "Virus Breath"],
    "stats": [444, 252, 56, 88, 59, 31, 49]
  },
  "Wicked Turret": {
    "name": "Wicked Turret",
    "area": "Adamah 236-243",
    "gem": "Sapphire",
    "lvl": 75,
    "race": "Chariot",
    "resists": "rrrs-w----",
    "skills": ["Strike Attack", "Power Charge", "Rakunda", "Tarukaja", "Myriad Arrows", "Agidyne"],
    "stats": [405, 172, 85, 25, 71, 23, 28]
  },
  "Wild Beast": {
    "name": "Wild Beast",
    "area": "Arqa I 17-24",
    "gem": "Onyx",
    "lvl": 10,
    "race": "Strength",
    "resists": "--s----ww-",
    "skills": ["Strike Attack", "Sonic Punch", "Tarukaja"],
    "stats": [148, 59, 12, 6, 12, 5, 2]
  },
  "Wild Drive": {
    "name": "Wild Drive",
    "area": "Yabbashah I 65-89",
    "gem": "Turquoise",
    "lvl": 34,
    "race": "Chariot",
    "resists": "sss--w----",
    "skills": ["Slash Attack", "Kill Rush", "Rakukaja"],
    "stats": [246, 105, 35, 7, 33, 26, 8]
  },
  "Wondrous Magus": {
    "name": "Wondrous Magus",
    "area": "Tziah II 151-163",
    "gem": "Onyx",
    "lvl": 52,
    "race": "Magician",
    "resists": "---wr--nw-",
    "skills": ["Strike Attack", "Bufula", "Mabufula", "Bufudyne", "Matarukaja", "Mahama", "Ice Boost"],
    "stats": [290, 165, 33, 57, 25, 20, 28]
  },
  "Wrathful Book": {
    "name": "Wrathful Book",
    "area": "Adamah 237-261",
    "gem": "Emerald",
    "lvl": 72,
    "race": "Priestess",
    "resists": "---wr-d---",
    "skills": ["Pierce Attack", "Magarudyne", "Poison Mist", "Bufudyne", "Evil Touch"],
    "stats": [247, 140, 39, 52, 42, 43, 47]
  },
  "Writhing Tiara": {
    "name": "Writhing Tiara",
    "area": "Tziah I 123-138",
    "gem": "Aquamarine",
    "lvl": 42,
    "race": "Priestess",
    "resists": "---nw-----",
    "skills": ["Strike Attack", "Agilao", "Dia", "Hama"],
    "stats": [176, 100, 24, 34, 23, 27, 25]
  }
}
"""


val gson = Gson()
val mapType = object : TypeToken<Map<String, Shadow>>() {}.type
val shadowMap: Map<String, Shadow> = gson.fromJson(json, mapType)

// Convert the map to a list of ShadowsData objects
val shadowsList = shadowMap.values.toList()

//https://developer.android.com/jetpack/compose/text/fonts
@RequiresApi(Build.VERSION_CODES.Q)
val minervaFamily = FontFamily(
    Font(R.font.minerva_modern_black),
    Font(R.font.minerva_modern_bold_italic),
    Font(R.font.minerva_modern_bold),
    Font(R.font.minerva_modern_italic),
    Font(R.font.minerva_modern_bold_italic)
)

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
@TypeConverters
//Que es este NavHostController
fun CompendiumScreen(navController: NavHostController, shadowDAO: ShadowDAO) {

    //Corutine
        LaunchedEffect(Unit) {
            shadowDAO.insertAll(shadowsList)
        }

    //val shadowViewModel = remember { ShadowViewModel() }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(15, 139, 237)),
        content = {
            items(shadowsList.size) { index ->
                val shadow = shadowsList[index]

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .background(Color(2, 46, 73))
                        .clickable {


                            //shadowViewModel.shadowSelector(shadow)
                            navController.navigate(Screen.DetailedShadow.route)
                        }
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
                        Box(
                            modifier = Modifier
                                .size(16.dp)
                                .background(Color(254, 189, 97))
                        )
                        Text(
                            text = shadow.name,
                            color = Color.White,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(start = 8.dp, top = 16.dp, bottom = 16.dp),
                            fontFamily = minervaFamily,
                            fontWeight = FontWeight.Normal,
                        )
                    }
                }
            }
        }
    )
}



/*RequiresApi(Build.VERSION_CODES.Q)
@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {


    CompendiumScreen()
}*/