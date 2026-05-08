package com.gitpurr.ids.sistemaAdopcion.domain

data class Animalito(
    val id: String? = null,
    var nombre: String,
    var edad: String,
    var especie: String,
    var raza: String? = null,
    var sexo: String,
    var tamano: String,
    var descripcion: String? = null,
    var codigoPostal: String,
    var latitudAprox: Double? = null,
    var longitudAprox: Double? = null,
    var fotoUrl: String? = null,
    var usuarioId: String? = null
)