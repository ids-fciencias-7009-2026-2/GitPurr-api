package com.gitpurr.ids.sistemaAdopcion.controllers

import com.gitpurr.ids.sistemaAdopcion.domain.Usuario
import com.gitpurr.ids.sistemaAdopcion.domain.toUsuario
import com.gitpurr.ids.sistemaAdopcion.dto.request.CreateUsuarioRequest
import com.gitpurr.ids.sistemaAdopcion.dto.request.LoginUsuarioRequest
import com.gitpurr.ids.sistemaAdopcion.dto.response.LoginResponse
import com.gitpurr.ids.sistemaAdopcion.dto.response.LogoutResponse
import com.gitpurr.ids.sistemaAdopcion.dto.response.UsuarioResponse
import com.gitpurr.ids.sistemaAdopcion.services.UsuarioService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.security.MessageDigest
import java.time.LocalDateTime
import java.util.UUID

/**
 * Controlador encargado de exponer los endpoints REST relacionados
 * con la gestión de usuarios en nuestra plataforma.
 *
 * De momento no se usará una base de datos, se trabajará con datos simulados.
 */
@RestController // Es mejor usar RestController para que retorne JSON automáticamente
@RequestMapping("/usuarios") // Base para los endpoints de este controlador

class UsuarioController {

    @Autowired
    lateinit var usuarioService: UsuarioService

    val logger: Logger = LoggerFactory.getLogger(UsuarioController::class.java)

    // ==========================================================
    // Endpoint que devuelve la información del usuario autenticado.
    // ==========================================================
    @GetMapping("/me")
    fun obtenerMiPerfil(
        @RequestHeader("Authorization", required = false) token: String?
    ): ResponseEntity<Usuario> {
        logger.info("Token recibido: $token")
        if (token == null) {
            return ResponseEntity.status(401).build()
        }
        // el header puede contener el prefijo Bearer, hay que omitirlo
        val soloToken = token.removePrefix("Bearer ").trim()
        // se busca en la base de datos al usuario con el token proporcionado
        val encontrado = usuarioService.findByToken(soloToken)

        if (encontrado == null) {
            return ResponseEntity.status(401).build()
        }

        logger.info("Usuario encontrado: $encontrado")
        // Retornamos HTTP 200 (OK) junto con el usuario enconrado
        return ResponseEntity.ok(encontrado)
    }


    // ==========================================================
    // Endpoint que registra un nuevo usuario.
    // ==========================================================
    @PostMapping("/register")
    fun agregaUsuario(
        @RequestBody createUsuarioRequest: CreateUsuarioRequest // Recibe JSON y lo transforma
    ): ResponseEntity<Usuario> {

        // Conversión de DTO a objeto de dominio usando una extension function
        val usuarioNuevo = createUsuarioRequest.toUsuario()
        // Se hashea la contraseña del usuario nuevo
        val password = hashPassword(createUsuarioRequest.password)
        usuarioNuevo.password = password
        usuarioService.addNewUsuario(usuarioNuevo)

        logger.info("Usuario a agregar: $usuarioNuevo")

        // Retornamos HTTP 200 (OK) junto con el usuario nuevo
        return ResponseEntity.ok(usuarioNuevo)
    }


    // ==========================================================
    // Endpoint para inicio de sesión (autenticación de un usuario)
    // ==========================================================
    @PostMapping("/login")
    fun login(@RequestBody request: LoginUsuarioRequest): ResponseEntity<Any> {
        // Se hashea la contraseña dada en el inicio de sesión
        val passwordHash = hashPassword(request.password)
        logger.info("Contraseña del request: $passwordHash")
        // Se busca al usuario con el email y el hash de la contraseña dados
        val encontrado = usuarioService.login(request.email, passwordHash)
        logger.info("intentando iniciar sesión con: $request")
        logger.info("contraseña: ${encontrado?.password}")

        return if (encontrado != null) {
            // Login exitoso: se devuelve HTTP 200 OK
            ResponseEntity.ok(LoginResponse(encontrado.token.orEmpty()))
        } else {
            // Login fallido: se devuelve HTTP 401 Unauthorized
            ResponseEntity.status(401).build()
        }
    }

    fun hashPassword(password: String): String {
        val bytes = MessageDigest
            .getInstance("SHA-256")
            .digest(password.toByteArray())
        return bytes.joinToString("") { "%02x".format(it) }
    }

    fun tokenGenerator(): String {
        val token = UUID.randomUUID().toString()
        return token
    }


    // ==========================================================
    // Endpoint para el cierre de sesión del usuario
    // ==========================================================
    @PostMapping("/logout")
    fun logout(
        @RequestHeader("Authorization") token: String?
    ): ResponseEntity<Any> {
        logger.info("Token recibido: $token")
        if(token == null) {
            return ResponseEntity.status(401).build()
        }
        val soloToken = token.removePrefix("Bearer ").trim()
        val encontrado = usuarioService.logout(soloToken)

        if (encontrado == null) {
            // logout fallido si no se encuentra el usuario
            return ResponseEntity.status(401).build()
        }

        val logoutResponse = LogoutResponse(
            encontrado.id ?: "N/A",
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