package com.gitpurr.ids.sistemaAdopcion.repositories

import com.gitpurr.ids.sistemaAdopcion.domain.Animalito
import com.gitpurr.ids.sistemaAdopcion.entities.AnimalitoEntity
import com.gitpurr.ids.sistemaAdopcion.entities.UsuarioEntity

fun Animalito.toAnimalitoEntity(usuarioEntity: UsuarioEntity): AnimalitoEntity {
    return AnimalitoEntity(
        nombre = this.nombre,
        edad = this.edad,
        especie = this.especie,
        raza = this.raza,
        sexo = this.sexo,
        tamano = this.tamano,
        descripcion = this.descripcion,
        codigoPostal = this.codigoPostal,
        latitudAprox = this.latitudAprox,
        longitudAprox = this.longitudAprox,
        fotoUrl = this.fotoUrl,
        usuario = usuarioEntity
    )
}