package com.gitpurr.ids.sistemaAdopcion.domain

import com.gitpurr.ids.sistemaAdopcion.dto.request.CreateUsuarioRequest
import com.gitpurr.ids.sistemaAdopcion.dto.response.UsuarioResponse
import com.gitpurr.ids.sistemaAdopcion.entities.UsuarioEntity
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

    // Creamos el objeto de dominio usando los datos del DTO
    return Usuario(
        nombre = this.nombre,
        email = this.email,
        password = this.password,
    )

}

fun UsuarioEntity.toUsuario(): Usuario {
    return Usuario(id = this.id.toString(), nombre = this.nombre, email = this.email, token = this.token)
}

