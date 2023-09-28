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
    var facultadId: Long,
    


)

data class EscuelaConFacultad(
    var id: Long,
    var nombreeap: String,
    var estado: String,
    var inicialeseap: String,
    var facultadId: Long,
    var nombrefac: String,
)
data class EscuelaReport(

    var id: Long,
    var nombreeap: String,
    var estado: String,
    var inicialeseap: String,
    var facultadId: Facultad,
)