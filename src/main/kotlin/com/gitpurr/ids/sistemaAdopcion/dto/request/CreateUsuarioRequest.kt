package com.gitpurr.ids.sistemaAdopcion.dto.request

/**
 * DTO (Data Transfer Object) utilizado para recibir los
 * datos necesarios para la creación de un nuevo usuario.
 *
 * Un objeto que representa el body de una petición HTTP
 * POST enviada por el cliente.
 *
 * Ejemplo: de JSON que lo construye automáticamente:
 *
 * {
 *   "nombre": "Brenda",
 *   "email": "brenda@email.com"
 * }
 *
 * Spring convierte el JSON en una instancia de esta clase
 * cuando usamos @RequestBody en el Controller.
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
