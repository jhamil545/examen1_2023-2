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

data class EscuelaConActividad(
    var id: Long,
    var cui: String,
    var tipoCui: String,
    var materEntre: String,
    var fecha: String,
    var horaReg: String,
    var latituda: String,
    var longituda: String,
    var modFh: String,
    var offlinex: String,
    var actividadId: Long,
    var nombreActividad: String
)
data class EscuelaReport(

    var id: Long,
    var nombreeap: String,
    var estado: String,
    var inicialeseap: String,
    var id_facultad: String,
)