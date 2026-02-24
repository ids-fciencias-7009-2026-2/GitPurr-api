package com.gitpurr.ids.sistemaAdopcion.dto.request.response

/**
 * DTO utilizado para enviar la información del usuario al cliente.
 * Su responsabilidad es transportar datos entre cliente y servidor[cite: 60, 61].
 * No contiene lógica de negocio y evita exponer modelos internos[cite: 69, 70, 71].
 */
data class UsuarioResponse(
    val id: Int,
    val nombre: String,
    val email: String
)