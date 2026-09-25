package com.example.forms.viewmodel

import androidx.lifecycle.ViewModel
import com.example.forms.model.UsuarioErrores
import com.example.forms.model.UsuarioUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class UsuarioViewModel : ViewModel() {

    private val _estado = MutableStateFlow(UsuarioUIState())

    val estado : StateFlow<UsuarioUIState> = _estado

    fun onNombreChange(valor : String){
        _estado.update {it.copy(nombre = valor, errores = it.errores.copy(nombre = null))}
    }

    fun onCorreoChange(valor : String){
        _estado.update {it.copy(correo = valor, errores = it.errores.copy(correo = null))}
    }

    fun onClaveChange(valor : String){
        _estado.update {it.copy(clave = valor, errores = it.errores.copy(clave = null))}
    }

    fun validarFormulario() : Boolean {
        //VALIDACION DE CAMPOS
        val estadoActual = _estado.value
        val errores = UsuarioErrores(
            nombre = if (estadoActual.nombre.isBlank()) "NO PUEDE ESTAR VACIO" else null,
            correo = if (!estadoActual.correo.contains("@")) "DEBE TENER ARROBA" else null,
            clave = if (estadoActual.clave.length < 8) "DEBE TENER AL MENOS 8 CARACTERES" else null
        )

        //HAY ERRORES SI LA LISTA NO ESTÁ VACIA Y CONTIENE NULOS
        //SI NO HAY ERRORES DEVOLVERÁ HAY ERRORES NEGADO
        val hayErrores = listOfNotNull(
            errores.correo,
            errores.correo,
            errores.clave
        ).isNotEmpty()

        _estado.update { it.copy(errores = errores) }

        return hayErrores
    }
}