package pe.edu.upeu.asistenciaupeujc.ui.presentation.screens.escuela


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pe.edu.upeu.asistenciaupeujc.modelo.Facultad
import pe.edu.upeu.asistenciaupeujc.modelo.Escuela
import pe.edu.upeu.asistenciaupeujc.modelo.EscuelaConFacultad
import pe.edu.upeu.asistenciaupeujc.repository.FacultadRepository
import pe.edu.upeu.asistenciaupeujc.repository.EscuelaRepository
import javax.inject.Inject

@HiltViewModel
class EscuelaViewModel @Inject constructor(
    private val escuRepo: EscuelaRepository,
) : ViewModel(){
    private val _isLoading: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }
    val activ: LiveData<List<EscuelaConFacultad>> by lazy {
        escuRepo.reportarEscuelas()
    }
    val isLoading: LiveData<Boolean> get() = _isLoading
    fun addEscuela() {
        if (_isLoading.value == false)
            viewModelScope.launch (Dispatchers.IO) {
                _isLoading.postValue(true)
            }
    }

    fun deleteEscuela(toDelete: EscuelaConFacultad) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.i("ELIMAR", toDelete.toString())
            escuRepo.deleteEscuela(toDelete);
        }
    }

}