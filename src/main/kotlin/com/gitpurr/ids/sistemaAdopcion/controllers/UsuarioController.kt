package com.gitpurr.ids.sistemaAdopcion.controllers

import com.gitpurr.ids.sistemaAdopcion.domain.Usuario
import com.gitpurr.ids.sistemaAdopcion.domain.toUsuario
import com.gitpurr.ids.sistemaAdopcion.dto.request.CreateUsuarioRequest
import com.gitpurr.ids.sistemaAdopcion.dto.request.LoginUsuarioRequest
import com.gitpurr.ids.sistemaAdopcion.dto.request.response.LogoutResponse
import com.gitpurr.ids.sistemaAdopcion.dto.request.response.UsuarioResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

/**
 * Controlador encargado de exponer los endpoints REST relacionados
 * con la gestión de usuarios en nuestra plataforma.
 *
 * De momento no se usará una base de datos, se trabajará con datos simulados.
 */
@RestController // Es mejor usar RestController para que retorne JSON automáticamente
@RequestMapping("/usuarios") // Base para los endpoints de este controlador
class UsuarioController {

    val logger: Logger = LoggerFactory.getLogger(UsuarioController::class.java)

    // ==========================================================
    // 1. GET /usuarios/me
    // ==========================================================
    @GetMapping("/me")
    fun obtenerMiPerfil(): ResponseEntity<UsuarioResponse> {
        // Simulamos que trajimos al usuario actual usando datos simulados
        val usuarioFake = UsuarioResponse(
            id = 1,
            nombre = "Brenda",
            email = "brenda@email.com"
        )
        // Retornamos HTTP 200 (OK)
        return ResponseEntity.ok(usuarioFake)
    }

    // ==========================================================
    // 2. POST /usuarios/register
    // ==========================================================
    @PostMapping("/register")
    fun agregaUsuario(
        @RequestBody createUsuarioRequest: CreateUsuarioRequest // Recibe JSON y lo transforma [cite: 135]
    ): ResponseEntity<Usuario> {

        // Conversión de DTO a objeto de dominio usando una extension function
        val usuarioNuevo = createUsuarioRequest.toUsuario()

        logger.info("Usuario a agregar: $usuarioNuevo")

        // Simulando la creación del usuario retornamos ResponseEntity [cite: 111]
        return ResponseEntity.ok(usuarioNuevo)
    }

    // ==========================================================
    // 3. POST /usuarios/login
    // ==========================================================
    @PostMapping("/login")
    fun login(@RequestBody request: LoginUsuarioRequest): ResponseEntity<Any> {
        // Simular validaciones básicas (por ejemplo, login correcto/incorrecto) [cite: 113]
        val emailCorrecto = "brenda@email.com"
        val passwordCorrecta = "12345"

        return if (request.email == emailCorrecto && request.password == passwordCorrecta) {
            // Login exitoso: HTTP 200 OK [cite: 137]
            ResponseEntity.ok(mapOf("mensaje" to "Login exitoso, ¡bienvenido!"))
        } else {
            // Login fallido: Es obligatorio devolver HTTP 401 Unauthorized [cite: 121, 137]
            ResponseEntity.status(401).body(mapOf("error" to "Credenciales incorrectas"))
        }
    }

    // ==========================================================
    // 4. POST /usuarios/logout
    // ==========================================================
    @PostMapping("/logout")
    fun logout(): ResponseEntity<Any> {

        val usuarioFake = Usuario(

            id = "1",
            nombre = "Brenda",
            email = "brenda@email.com"
        )

        val logoutResponse = LogoutResponse(
            usuarioFake.id,
            LocalDateTime.now().toString()
        )

        return ResponseEntity.ok(logoutResponse)
    }

    // ==========================================================
    // 5. PUT /usuarios
    // ==========================================================
    @PutMapping
    fun actualizarUsuario(
        @RequestBody request: CreateUsuarioRequest
    ): ResponseEntity<UsuarioResponse> {

        // Como no hay BD ni autenticación real, simulamos que el usuario actual es el id = 1
        val usuarioActualizado = UsuarioResponse(
            id = 1,
            nombre = request.nombre,
            email = request.email
        )

        logger.info("Usuario actualizado (simulado): $usuarioActualizado")

        // HTTP 200 OK
        return ResponseEntity.ok(usuarioActualizado)
    }
}