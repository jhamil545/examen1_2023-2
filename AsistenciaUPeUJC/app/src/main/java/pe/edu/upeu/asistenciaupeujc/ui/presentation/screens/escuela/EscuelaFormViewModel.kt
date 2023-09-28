package pe.edu.upeu.asistenciaupeujc.ui.presentation.screens.escuela

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

import pe.edu.upeu.asistenciaupeujc.modelo.ComboModel
import pe.edu.upeu.asistenciaupeujc.modelo.Escuela
import pe.edu.upeu.asistenciaupeujc.modelo.EscuelaReport
import pe.edu.upeu.asistenciaupeujc.modelo.Facultad

import pe.edu.upeu.asistenciaupeujc.repository.EscuelaRepository
import pe.edu.upeu.asistenciaupeujc.repository.FacultadRepository
import javax.inject.Inject

@HiltViewModel
class EscuelaFormViewModel @Inject constructor(
    private val escuRepo: EscuelaRepository,
    private val facuRepo: FacultadRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel(){
    private val _isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }

    fun getEscuela(idX: Long): LiveData<Escuela> {
        return escuRepo.buscarEscuelaId(idX)
    }
    val isLoading: LiveData<Boolean> get() = _isLoading

    val activ: LiveData<List<Facultad>> by lazy { facuRepo.reportarFacultades()}
    var listE = mutableListOf<ComboModel>(ComboModel(0.toString(), "Seleccione"))

    init {
        viewModelScope.launch {
            _isLoading.postValue(true)
            delay(1500)
            activ.value?.forEach {
                listE.add(ComboModel(code = it.id.toString(), name = it.nombrefac))
            }
            //listE.removeAt(0)
            _isLoading.postValue(false)
        }
    }

    fun addEscuela(escuela: Escuela){
        viewModelScope.launch (Dispatchers.IO){
            try {
                escuRepo.insertarEscuela(escuela)
            }catch (e:Exception){
                Log.i("ERRRRR", "${e.message}")
            }
        }
    }
    fun editEscuela(escuela: Escuela){
        viewModelScope.launch(Dispatchers.IO){
            try {
                escuRepo.modificarRemoteEscuela(escuela)
            }catch (e:Exception){
                Log.i("ERRRRREDI", "${e.message}")
            }
        }
    }
}