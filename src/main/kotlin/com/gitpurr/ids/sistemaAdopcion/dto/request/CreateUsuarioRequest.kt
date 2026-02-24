package com.gitpurr.ids.sistemaAdopcion.dto.request

/**
 * DTO (Data Transfer Object) utilizado para recibir los
 * datos necesarios para la creación de un nuevo usuario.
 */
data class CreateUsuarioRequest(

    /**
     * Nombre del usuario enviado por el cliente.
     */
    val nombre: String,

    /**
     * Correo electrónico enviado por el cliente.
     */
    val email: String
)