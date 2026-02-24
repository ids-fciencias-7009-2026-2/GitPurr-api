package com.gitpurr.ids.sistemaAdopcion.domain

import com.gitpurr.ids.sistemaAdopcion.dto.request.CreateUsuarioRequest
import java.util.UUID

/**
 * Extension Function (permite aregar la función toUsuario() al DTO
 * CreateUsuarioRequest) que convierte el DTO CreateUsuarioRequest
 * en un objeto de dominio Usuario.
 *
 * El objeto de dominio representa el modelo interno del sistema.
 *
 * Esta función genera un identificador simulado y crea un objeto
 * Usuario usando los datos proporcionados por el DTO.
 */
fun CreateUsuarioRequest.toUsuario(): Usuario {

    // Generamos un identificador de forma aleatoria
    val id = "id-random-" + UUID.randomUUID().toString()

    // Creamos el objeto de dominio usando los datos del DTO
    return Usuario(
        id = id,
        nombre = this.nombre,
        email = this.email
    )
}
