package pe.edu.upeu.asistenciaupeujc.ui.presentation.screens.escuela

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pe.edu.upeu.asistenciaupeujc.modelo.Escuela
import pe.edu.upeu.asistenciaupeujc.repository.EscuelaRepository
import javax.inject.Inject

@HiltViewModel
class EscuelaFormViewModel @Inject constructor(
    private val escuRepo: EscuelaRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel(){
    private val _isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }

    fun getEscuela(idX: Long): LiveData<Escuela> {
        return escuRepo.buscarEscuelaId(idX)
    }

    val isLoading: LiveData<Boolean> get() = _isLoading


    fun addActividad(escuela: Escuela){
        viewModelScope.launch (Dispatchers.IO){
            Log.i("REAL", escuela.toString())
            escuRepo.insertarEscuela(escuela)
        }
    }
    fun editActividad(actividad: Escuela){
        viewModelScope.launch(Dispatchers.IO){
            escuRepo.modificarRemoteEscuela(actividad)
        }
    }
}