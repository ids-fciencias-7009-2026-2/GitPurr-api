package com.gitpurr.ids.sistemaAdopcion.domain

import com.gitpurr.ids.sistemaAdopcion.dto.request.CreateAnimalitoRequest
import com.gitpurr.ids.sistemaAdopcion.dto.response.AnimalitoResponse
import com.gitpurr.ids.sistemaAdopcion.dto.response.UbicacionResponse
import com.gitpurr.ids.sistemaAdopcion.entities.AnimalitoEntity

fun CreateAnimalitoRequest.toAnimalito(): Animalito {
    return Animalito(
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
        fotoUrl = this.fotoUrl
    )
}

fun AnimalitoEntity.toAnimalito(): Animalito {
    return Animalito(
        id = this.id.toString(),
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
        usuarioId = this.usuario?.id.toString()
    )
}

fun Animalito.toAnimalitoResponse(): AnimalitoResponse {
    return AnimalitoResponse(
        id = this.id,
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
        usuarioId = this.usuarioId
    )
}

fun Animalito.toUbicacionResponse(): UbicacionResponse {
    return UbicacionResponse(
        lat = this.latitudAprox!!,
        lng = this.longitudAprox!!
    )
}