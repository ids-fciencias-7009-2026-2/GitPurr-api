package com.gitpurr.ids.sistemaAdopcion.controllers

import com.gitpurr.ids.sistemaAdopcion.domain.Usuario
import com.gitpurr.ids.sistemaAdopcion.domain.toUsuario
import com.gitpurr.ids.sistemaAdopcion.dto.request.CreateUsuarioRequest
// import com.gitpurr.ids.sistemaAdopcion.dto.request.LoginRequest
// import com.gitpurr.ids.sistemaAdopcion.dto.request.UpdateUsuarioRequest
// import com.gitpurr.ids.sistemaAdopcion.dto.response.LogoutResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

/**
 * Controlador encargado de exponer los endpoints REST relacionados
 * con la gestión de usuarios en nuestra plataforma.
 *
 * Esta es la capa externa del sistema y sirve para recibir las peticiones HTTP,
 * transformarlas en objetos Kotlin y devolver respuestas HTTP adecuadas.
 *
 * De momento no se usará una base de datos, se trabajará con datos simulados.
 */
@Controller
@RequestMapping("/usuarios") // Base para los endpoints de este controlador
class UsuarioController {

    /**
     * Logger para registrar eventos importantes del flujo de ejecución.
     *
     * Permite imprimir información útil en consola para depuración,
     * auditoría y análisis del comportamiento del sistema.
     */
    val logger: Logger = LoggerFactory.getLogger(UsuarioController::class.java)

    /**
     * Endpoint para registrar un nuevo usuario.
     *
     * Recibe un JSON con los datos del usuario
     * y los transforma en un objeto de dominio.
     *
     * URL:    http://localhost:8080/usuarios/register
     * Metodo: POST
     *
     * @param createUsuarioRequest DTO que representa el body del request.
     * @return ResponseEntity con el usuario creado y código HTTP 200 (OK).
     */
    @PostMapping("/register")
    fun agregaUsuario(
        @RequestBody createUsuarioRequest: CreateUsuarioRequest
    ): ResponseEntity<Usuario> {

        // Conversión de DTO a objeto de dominio usando una extension function
        val usuarioNuevo = createUsuarioRequest.toUsuario()

        logger.info("Usuario a agregar: $usuarioNuevo")

        // Simulando la creación del usuario
        return ResponseEntity.ok(usuarioNuevo)
    }


}