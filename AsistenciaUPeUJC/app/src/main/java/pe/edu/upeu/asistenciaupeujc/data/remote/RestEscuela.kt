package pe.edu.upeu.asistenciaupeujc.data.remote

import pe.edu.upeu.asistenciaupeujc.modelo.Escuela
import pe.edu.upeu.asistenciaupeujc.modelo.EscuelaReport
import pe.edu.upeu.asistenciaupeujc.modelo.MsgGeneric
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface RestEscuela {
    @GET("/asis/escuela/list")
    suspend fun reportarEscuela(@Header("Authorization") token:String):Response<List<EscuelaReport>>


    @GET("/asis/escuela/buscar/{id}")
    suspend fun getEscuelaId(@Header("Authorization") token:String , @Query("id") id:Long):Response<Escuela>


    @DELETE("/asis/escuela/eliminar/{id}")
    suspend fun deleteEscuela(@Header("Authorization") token:String , @Path("id") id:Long):Response<MsgGeneric>


    @PUT("/asis/escuela/editar/{id}")
    suspend fun actualizarEscuela(@Header("Authorization") token:String , @Path("id") id:Long , @Body escuela :Escuela):Response<Escuela>

    @POST("/asis/escuela/crear")
    suspend fun insertarEscuela(@Header("Authorization") token:String , @Body escuela :Escuela):Response<Escuela>


}