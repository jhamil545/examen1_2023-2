package pe.edu.upeu.asistenciaupeujc.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import pe.edu.upeu.asistenciaupeujc.data.local.dao.EscuelaDao
import pe.edu.upeu.asistenciaupeujc.modelo.Escuela

@Database(entities =  [Escuela::class], version = 1)
abstract class DbDataSource:RoomDatabase(){
    abstract fun escuelaDao():EscuelaDao
}