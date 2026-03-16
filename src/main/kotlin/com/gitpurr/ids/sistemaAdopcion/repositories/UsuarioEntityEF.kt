package com.gitpurr.ids.sistemaAdopcion.repositories

import com.gitpurr.ids.sistemaAdopcion.domain.Usuario
import com.gitpurr.ids.sistemaAdopcion.entities.UsuarioEntity

fun Usuario.toUsuarioEntity(): UsuarioEntity {
    return UsuarioEntity(
        email = this.email,
        password = this.password ?: "",
        nombre = this.nombre,
        token = ""
    )
}