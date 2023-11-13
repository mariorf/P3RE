package com.example.p3re.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

//EL converter es para que al insertar en la base de datos Listas (en este caso una de string y otra de int) se pase a cadena JSON y al sacarlo vuelva a su estado de lista
//version per si tinguera que actualitzar o migrar la ddbb
//NO VA :( :( :( :( :(: :( :(
@Database(entities = [SHHADOW::class], version = 1)
@TypeConverters(Converters::class)
abstract class ShadowDatabase: RoomDatabase() {

    abstract val shadowDao: ShadowDAO
}