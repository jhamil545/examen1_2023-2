package pe.edu.upeu.asistenciaupeujc.repository

import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pe.edu.upeu.asistenciaupeujc.data.local.dao.EscuelaDao
import pe.edu.upeu.asistenciaupeujc.data.remote.RestEscuela
import pe.edu.upeu.asistenciaupeujc.modelo.Escuela
import pe.edu.upeu.asistenciaupeujc.modelo.EscuelaConActividad
import pe.edu.upeu.asistenciaupeujc.utils.TokenUtils
import javax.inject.Inject

interface EscuelaRepository {

    suspend fun deleteEscuela(escuela: EscuelaConActividad)
    fun reportarEscuelaes(): LiveData<List<EscuelaConActividad>>

    fun buscarEscuelaId(id:Long): LiveData<Escuela>

    suspend fun insertarEscuela(escuela: Escuela):Boolean

    suspend fun modificarRemoteEscuela(escuela: Escuela):Boolean
}

class EscuelaRepositoryImp @Inject constructor(
    private val restEscuela: RestEscuela,
    private val escuelaDao: EscuelaDao
): EscuelaRepository{
    override suspend fun deleteEscuela(escuela: EscuelaConActividad){
        CoroutineScope(Dispatchers.IO).launch {
            restEscuela.deleteEscuela(TokenUtils.TOKEN_CONTENT,escuela.id)
        }
        escuelaDao.eliminarEscuela(escuela.id)
    }


    override fun reportarEscuelaes(): LiveData<List<EscuelaConActividad>> {
        try {
            CoroutineScope(Dispatchers.IO).launch{
                delay(3000)
                val data=restEscuela.reportarEscuela(TokenUtils.TOKEN_CONTENT).body()!!
                val dataxx = data.map {
                    Escuela(it.id, it.nombreeap, it.estado,it.inicialeseap, it.id_facultad)
                }
                escuelaDao.insertarEscuelas(dataxx)
            }

        }catch (e:Exception){
            Log.i("ERROR", "Error: ${e.message}")
        }
        return escuelaDao.reportatEscuela()
    }

    override fun buscarEscuelaId(id:Long): LiveData<Escuela> {
        return  escuelaDao.buscarEscuela(id)
    }


    override suspend fun insertarEscuela(escuela: Escuela):Boolean{
        //Log.i("DATAXXX", "${escuela.actividadId}")
        return restEscuela.insertarEscuela(TokenUtils.TOKEN_CONTENT, escuela).body()!=null
    }

    override suspend fun modificarRemoteEscuela(escuela: Escuela):Boolean{

        CoroutineScope(Dispatchers.IO).launch {
            Log.i("VER", TokenUtils.TOKEN_CONTENT)
        }
        return restEscuela.actualizarEscuela(TokenUtils.TOKEN_CONTENT, escuela.id, escuela).body()!=null
    }
}