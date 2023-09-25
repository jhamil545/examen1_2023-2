package pe.edu.upeu.asistenciaupeujc.modelo

data class Persona(
    var id: Long,
    var codigo: String,
    var nombre: String,
    var apellido_pat: String,
    var apellido_mat: String,
    var celular: String,
    var correo: String,
    var clave: String,
    var tipo: String,
    var estado: String,
    var escuelaId: String,
)
