package com.gitpurr.ids.sistemaAdopcion.dto.request

/**
 * DTO utilizado para recibir las credenciales de inicio de sesión.
 */
data class LoginUsuarioRequest(
    val email: String,
    val password: String
)