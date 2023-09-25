package pe.edu.upeu.asistenciaupeujc.modelo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "escuela")
data class Escuela(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var nombreeap: String,
    var estado: String,
    var inicialeseap: String,
    var id_facultad: String,
)
data class EscuelaReport(

    var id: Long,
    var nombreeap: String,
    var estado: String,
    var inicialeseap: String,
    var id_facultad: String,
)
