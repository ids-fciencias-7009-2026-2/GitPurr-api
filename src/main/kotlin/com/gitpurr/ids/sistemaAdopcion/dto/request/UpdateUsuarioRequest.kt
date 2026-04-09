package com.gitpurr.ids.sistemaAdopcion.dto.request

/**
 * DTO (Data Transfer Object) utilizado para recibir los
 * datos necesarios para la actualización de un usuario.
 */
data class UpdateUsuarioRequest(

    /**
     * Nombre actualizado del usuario.
     */
    val nombre: String? = null,

    /**
     * Correo actualizado del usuario.
     */
    val email: String? = null,

    /**
     * Contraseña actualizada del usuario. Requiere
     * la password actual por motivos de seguridad.
     */
    val passwordOld: String? = null,
    val passwordNew: String? = null
)
