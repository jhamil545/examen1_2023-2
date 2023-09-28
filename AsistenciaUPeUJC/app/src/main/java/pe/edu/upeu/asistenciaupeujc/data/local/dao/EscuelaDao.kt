package pe.edu.upeu.asistenciaupeujc.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Embedded
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Relation
import androidx.room.Transaction
import androidx.room.Update
import pe.edu.upeu.asistenciaupeujc.modelo.Actividad
import pe.edu.upeu.asistenciaupeujc.modelo.Escuela
import pe.edu.upeu.asistenciaupeujc.modelo.EscuelaConFacultad

@Dao
interface EscuelaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarEscuela(escuela: Escuela)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarEscuelas(escuela: List<Escuela>)

    @Update
    suspend fun modificarEscuela(escuela: Escuela)

    @Query("delete  from escuela where id=:escuela")
    suspend fun eliminarEscuela(escuela: Long)
    /*@Delete
    suspend fun eliminarEscuela(escuela: Escuela)*/
    @Transaction
    @Query("select m.*, a.nombrefac from escuela m, facultad a where m.facultadId=a.id")
    fun reportatEscuela():LiveData<List<EscuelaConFacultad>>



    @Query("select * from escuela where id=:idx")
    fun buscarEscuela(idx: Long): LiveData<Escuela>
}