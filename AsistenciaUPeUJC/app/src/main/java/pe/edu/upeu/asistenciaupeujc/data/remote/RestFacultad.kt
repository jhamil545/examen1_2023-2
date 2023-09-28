package pe.edu.upeu.asistenciaupeujc.data.remote

import pe.edu.upeu.asistenciaupeujc.modelo.Facultad
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

interface RestFacultad {
    @GET("/upeu/facultad/list")
    suspend fun reportarFacultad(@Header("Authorization") token:String):Response<List<Facultad>>

    @GET("/upeu/facultad/buscar/{id}")
    suspend fun getFacultadId(@Header("Authorization") token:String, @Query("id") id:Long):Response<Facultad>

    @DELETE("/upeu/facultad/eliminar/{id}")
    suspend fun deleteFacultad(@Header("Authorization") token:String, @Path("id") id:Long):Response<MsgGeneric>

    @PUT("/upeu/facultad/editar/{id}")
    suspend fun actualizarFacultad(@Header("Authorization") token:String, @Path("id") id:Long, @Body facultad: Facultad): Response<Facultad>

    @POST("/upeu/facultad/crear")
    suspend fun insertarFacultad(@Header("Authorization") token:String,@Body facultad: Facultad): Response<Facultad>
}