import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type



object Converters {
    /*@TypeConverter
    fun fromString(value: String?): List<String> {
        val listType = object : TypeToken<List<String?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromStringList(list: List<String?>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }*/

    @TypeConverter
    fun listToJsonString(value: List<String>?): String? = Gson().toJson(value)


    @TypeConverter
    fun jsonStringToList(value: String?) =
        Gson().fromJson(value, Array<String>::class.java).toList()

    @TypeConverter
    fun fromInt(value: String?): List<Int?>? {
        val listType: Type = object : TypeToken<List<Int?>?>() {}.type
        return Gson().fromJson<List<Int>>(value, listType)
    }

    @TypeConverter
    fun fromIntList(list: List<Int?>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }
}