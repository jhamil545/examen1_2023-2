package pe.edu.upeu.asistenciaupeujc.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import pe.edu.upeu.asistenciaupeujc.data.local.dao.ActividadDao
import pe.edu.upeu.asistenciaupeujc.data.local.dao.FacultadDao
import pe.edu.upeu.asistenciaupeujc.data.local.dao.EscuelaDao
import pe.edu.upeu.asistenciaupeujc.data.local.dao.MaterialesxDao
import pe.edu.upeu.asistenciaupeujc.modelo.Actividad
import pe.edu.upeu.asistenciaupeujc.modelo.Facultad
import pe.edu.upeu.asistenciaupeujc.modelo.Escuela
import pe.edu.upeu.asistenciaupeujc.modelo.Materialesx

@Database(entities = [Actividad::class, Materialesx::class, Escuela::class, Facultad::class], version = 4)
abstract class DbDataSource:RoomDatabase() {
    abstract fun actividadDao(): ActividadDao

    abstract fun materialesxDao(): MaterialesxDao

    abstract fun facultadDao():FacultadDao
    abstract fun escuelaDao():EscuelaDao
}