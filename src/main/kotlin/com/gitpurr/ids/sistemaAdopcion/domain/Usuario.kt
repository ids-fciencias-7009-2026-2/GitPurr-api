package com.gitpurr.ids.sistemaAdopcion.domain

/**
 * Representa el modelo de dominio "Usuario" dentro del sistema,
 * es decir; la representación interna de un usuario. Contiene
 * los datos con los que el sistema trabaja.
 *
 * password es nullable porque no siempre se necesita la contraseña.
 *
 * Para indicar que un valor puede ser null en Kotlin, usamos "?".
 */
data class Usuario(

    /**
     * Identificador único del usuario.
     * No se puede modificar.
     */
    val id: String,

    /**
     * Nombre del usuario.
     * Se define como "var" porque puede actualizarse.
     */
    var nombre: String,

    /**
     * Correo electrónico del usuario.
     * También puede actualizarse.
     */
    var email: String,

    /**
     * Contraseña del usuario.
     * Es nullable y no siempre se necesita.
     */
    var password: String? = null
)
