package com.example.p3re.data

import com.example.p3re.R

object SocialLinksImageList {

    //https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-pair/
    //Se muestran en orden de llegada
    fun loadImages(): List<Pair<Int, String>> {
        return listOf(
            R.drawable.protag to "Makoto Yuki",
            R.drawable.yukari to "Yukari Takeba",
            R.drawable.junpei to "Junpei Iori",
            R.drawable.akihiko to "Akihiko Sanada",
            R.drawable.mitsuru to "Mitsuru Kirijo",
            R.drawable.fuuka to "Fuuka Yamagishi",
            R.drawable.koromaru to "Koromaru",
            R.drawable.aigis to "Aigis",
            R.drawable.ken to "Ken Amada",
            R.drawable.shinjiro to "Shinjiro",
            R.drawable.kenji to "Kenji",
            R.drawable.yuko to "Yuko",
            R.drawable.kazushi to "Kazushi",
            R.drawable.keisuke to "Keisuke",
            R.drawable.chihiro to "Chihiro",
            R.drawable.hidetoshi to "Hidetoshi",
            R.drawable.andre to "Andre",
        )
    }

}