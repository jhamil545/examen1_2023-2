package pe.edu.upeu.asistenciaupeujc.repository

import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import pe.edu.upeu.asistenciaupeujc.data.local.dao.EscuelaDao
import pe.edu.upeu.asistenciaupeujc.data.remote.RestEscuela
import pe.edu.upeu.asistenciaupeujc.modelo.Escuela
import pe.edu.upeu.asistenciaupeujc.utils.TokenUtils
import javax.inject.Inject

interface EscuelaRepository {
    suspend fun deleteEscuela(escuela: Escuela)

    fun reportarEscuela():LiveData<List<Escuela>>

    fun buscarEscuelaId(id:Long):LiveData<Escuela>

    suspend fun insertarEscuela(escuela: Escuela):Boolean

    suspend fun modificarRemoteEscuela(escuela: Escuela):Boolean
}

class EscuelaRepositoryImp @Inject constructor(
    private val resEscuela:RestEscuela,
    private val escuelaDao:EscuelaDao,

):EscuelaRepository{
    override suspend fun deleteEscuela(escuela: Escuela){
        CoroutineScope(Dispatchers.IO).launch {
            resEscuela.deleteEscuela(TokenUtils.TOKEN_CONTENT,escuela.id)
        }
        escuelaDao.eliminarEscuela(escuela)
    }

    override fun reportarEscuela():LiveData<List<Escuela>>{
        try{
            CoroutineScope(Dispatchers.IO).launch {
                delay(3000)
                val data=resEscuela.reportarEscuela(TokenUtils.TOKEN_CONTENT).body()!!
                escuelaDao.insertarEscuela(data)
            }
    }catch (e:Exception){
        Log.i("ERROR","Error:${e.message}")

        }
        return escuelaDao.reportarEscuela()
         }


    override fun buscarEscuelaId(id:Long):LiveData<Escuela>{
        return  escuelaDao.buscarEscuela(id)
    }

    override suspend fun insertarEscuela(escuela: Escuela):Boolean{
        return resEscuela.insertarEscuela(TokenUtils.TOKEN_CONTENT,escuela).body()!=null
    }

    override suspend fun modificarRemoteEscuela(escuela: Escuela):Boolean{
        var dd:Boolean=false
        CoroutineScope(Dispatchers.IO).launch {
            Log.i("VER",TokenUtils.TOKEN_CONTENT)
        }
        return resEscuela.actualizarEscuela(TokenUtils.TOKEN_CONTENT, escuela.id, escuela).body()!=null
    }
}