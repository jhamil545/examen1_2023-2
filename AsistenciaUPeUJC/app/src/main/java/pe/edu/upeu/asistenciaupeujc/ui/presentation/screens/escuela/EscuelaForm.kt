package pe.edu.upeu.asistenciaupeujc.ui.presentation.screens.escuela


import android.annotation.SuppressLint
import android.os.Looper
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.github.k0shk0sh.compose.easyforms.BuildEasyForms
import com.github.k0shk0sh.compose.easyforms.EasyFormsResult
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.gson.Gson
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pe.edu.upeu.asistenciaupeujc.modelo.Facultad
import pe.edu.upeu.asistenciaupeujc.modelo.ComboModel
import pe.edu.upeu.asistenciaupeujc.modelo.Escuela
import pe.edu.upeu.asistenciaupeujc.modelo.EscuelaReport
import pe.edu.upeu.asistenciaupeujc.ui.navigation.Destinations
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.ProgressBarLoading
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.Spacer
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.form.AccionButtonCancel
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.form.AccionButtonSuccess
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.form.ComboBox
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.form.ComboBoxTwo
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.form.DatePickerCustom
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.form.DropdownMenuCustom
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.form.MyFormKeys
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.form.NameTextField
import pe.edu.upeu.asistenciaupeujc.ui.presentation.components.form.TimePickerCustom
import pe.edu.upeu.asistenciaupeujc.ui.presentation.screens.escuela.EscuelaFormViewModel
import pe.edu.upeu.asistenciaupeujc.utils.TokenUtils

@Composable
fun EscuelaForm(
    text: String,
    darkMode: MutableState<Boolean>,
    navController: NavHostController,
    viewModel: EscuelaFormViewModel = hiltViewModel()
) {
    val escuelaD:Escuela
    if (text!="0"){
        escuelaD = Gson().fromJson(text, Escuela::class.java)
    }else{
        escuelaD= Escuela(0,"","", "",0)
    }

    formulario(escuelaD.id!!,
        darkMode,
        navController,
        escuelaD,
        viewModel
    )

}



@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "MissingPermission",
    "CoroutineCreationDuringComposition"
)
@Composable
fun formulario(id:Long,
               darkMode: MutableState<Boolean>,
               navController: NavHostController,
               escuela:Escuela,
               viewModel: EscuelaFormViewModel
){

    Log.i("VERRR", "d: "+escuela?.id!!)
    val person=Escuela(0,"","", "",0)
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val isLoading by viewModel.isLoading.observeAsState(false)
    val actis by viewModel.activ.observeAsState(arrayListOf())

    var locationCallback: LocationCallback? = null
    var fusedLocationClient: FusedLocationProviderClient? = null
    fusedLocationClient = LocationServices.getFusedLocationProviderClient(
        context)



    Scaffold(modifier = Modifier.padding(top = 60.dp, start = 16.dp, end = 16.dp, bottom = 32.dp)){
        BuildEasyForms { easyForm ->
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {

                NameTextField(easyForms = easyForm, text =escuela?.nombreeap!!,"Nombre", MyFormKeys.NOMBREEAP )
                NameTextField(easyForms = easyForm, text =escuela?.inicialeseap!!,"iniciales", MyFormKeys.INICIALESEAP )


                var listE = listOf(
                    ComboModel("SI","SI"),
                    ComboModel("NO","NO"),
                )
                DropdownMenuCustom(easyForm = easyForm, label = "Estado", escuela.estado, list =listE, MyFormKeys.ESTADO )
                DropdownMenuCustom(easyForm = easyForm, label = "Facultad:", textv ="", viewModel.listE, MyFormKeys.FACULTADID )


                Row(Modifier.align(Alignment.CenterHorizontally)){
                    AccionButtonSuccess(easyForms = easyForm, "Guardar", id){
                        val lista=easyForm.formData()

                        person.nombreeap=(lista.get(0) as EasyFormsResult.StringResult).value
                        person.estado=splitCadena((lista.get(1) as EasyFormsResult.GenericStateResult<String>).value)
                        person.inicialeseap=(lista.get(2) as EasyFormsResult.StringResult).value
                        //person.actividadId = (lista.get(7) as EasyFormsResult.StringResult).value.toLong()
                        person.facultadId=
                            pe.edu.upeu.asistenciaupeujc.ui.presentation.screens.escuela.splitCadena(
                                (lista.get(3) as EasyFormsResult.GenericStateResult<String>).value
                            ).toLong()

                        if (id==0.toLong()){

                            Log.i("AGREGAR", "ES:"+ person.estado)
                            Log.i("AGREGARID", "OF:"+ person.facultadId)

                            viewModel.addEscuela(person)

                            navController.navigate(Destinations.EscuelaUI.route)
                        }else{
                            person.id=id
                            Log.i("MODIFICAR", "M:"+person)
                            viewModel.editEscuela(person)
                            navController.navigate(Destinations.EscuelaUI.route)
                        }

                    }
                    Spacer()
                    AccionButtonCancel(easyForms = easyForm, "Cancelar"){
                        navController.navigate(Destinations.EscuelaUI.route)
                    }
                }
            }
        }
    }

}


fun splitCadena(data:String):String{
    return if(data!="") data.split("-")[0] else ""
}