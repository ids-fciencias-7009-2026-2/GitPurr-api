package com.gitpurr.ids.sistemaAdopcion.dto.response

data class AnimalitoResponse(
    val id: String?,
    val nombre: String,
    val edad: String,
    val especie: String,
    val raza: String?,
    val sexo: String,
    val tamano: String,
    val descripcion: String?,
    val codigoPostal: String,
    val latitudAprox: Double?,
    val longitudAprox: Double?,
    val fotoUrl: String?,
    val usuarioId: String?
)