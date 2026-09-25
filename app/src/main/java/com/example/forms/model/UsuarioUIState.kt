package com.example.forms.model

data class UsuarioUIState(
    val nombre : String = "",
    val correo : String = "",
    val clave : String = "",
    val errores : UsuarioErrores = UsuarioErrores()
)
