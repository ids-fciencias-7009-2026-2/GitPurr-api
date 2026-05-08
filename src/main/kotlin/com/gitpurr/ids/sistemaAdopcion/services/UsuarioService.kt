package com.gitpurr.ids.sistemaAdopcion.services

import com.gitpurr.ids.sistemaAdopcion.domain.Usuario
import com.gitpurr.ids.sistemaAdopcion.domain.toUbicacionResponse
import com.gitpurr.ids.sistemaAdopcion.domain.toUsuario
import com.gitpurr.ids.sistemaAdopcion.dto.response.UbicacionResponse
import com.gitpurr.ids.sistemaAdopcion.repositories.UsuarioRepository
import com.gitpurr.ids.sistemaAdopcion.repositories.toUsuarioEntity
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.security.MessageDigest
import java.util.UUID

@Service
class UsuarioService {
    val logger = LoggerFactory.getLogger(UsuarioService::class.java)

    @Autowired
    lateinit var usuarioRepository: UsuarioRepository
    @Autowired
    lateinit var geocodioService: GeocodioService


    fun searchAllUsuarios(): List<Usuario> {
        val allUsers = usuarioRepository.findAll()

        logger.info("all users: ${allUsers.toString()}")
        return allUsers.map { usuarioEntity -> usuarioEntity.toUsuario() }
    }

    fun addNewUsuario(usuario: Usuario): Usuario {

        val(lat,lng) = geocodioService.obtenerCoordenadas(usuario.codigoPostal);

        usuario.latitud = lat
        usuario.longitud = lng
        val usuarioEntity = usuario.toUsuarioEntity()
        usuarioRepository.save(usuarioEntity)
        usuario.password = "****"
        return usuario
    }

    fun login(email: String, password: String): Usuario? {
        val usuarioEntity = usuarioRepository
            .findUserByPasswordAndEmail(email, password)

        if (usuarioEntity != null) {
            val token = tokenGenerator()
            usuarioEntity.token = token
            usuarioRepository.save(usuarioEntity)
        }
        return usuarioEntity?.toUsuario()
    }

    fun logout(token: String): Usuario? {
        val usuarioEntity = usuarioRepository
            .findByToken(token)

        if (usuarioEntity != null) {
            usuarioEntity.token = null
            usuarioRepository.save(usuarioEntity)
        }
        return usuarioEntity?.toUsuario()
    }

    fun findByToken(token: String): Usuario? {
        val usuarioLogged = usuarioRepository.findByToken(token)
        logger.info("Usuario exists: ${usuarioLogged.toString()}")
        return usuarioLogged?.toUsuario()
    }

    fun tokenGenerator(): String {
        val token = UUID.randomUUID().toString()
        return token
    }

    fun actualizarUsuario(
        token: String,
        nombre: String?,
        email: String?,
        passwordOld: String?,
        passwordNew: String?
    ): Usuario? {
        val usuarioEntity = usuarioRepository.findByToken(token)

        if (usuarioEntity == null) {
            logger.warn("Usuario no encontrado para token: $token")
            return null
        }

        // actualización de nombre
        if (!nombre.isNullOrBlank()) {
            usuarioEntity.nombre = nombre
        }

        // actualización de email
        if (!email.isNullOrBlank()) {
            usuarioEntity.email = email
        }

        // actualización de contraseña
        if (!passwordNew.isNullOrBlank()) {
            // se requiere la contraseña actual para actualizar
            if (passwordOld.isNullOrBlank()) {
                // excepción si no se proporciona
                throw IllegalArgumentException("PASSWORD_REQUIRED")
            }

            val passwordOldHash = hashPassword(passwordOld)

            if (usuarioEntity.password != passwordOldHash) {
                // excepción si la contraseña actual no coincide
                throw IllegalArgumentException("INVALID_PASSWORD")
            }
            usuarioEntity.password = passwordNew
        }

        usuarioRepository.save(usuarioEntity)
        return usuarioEntity.toUsuario()
    }

    fun hashPassword(password: String): String {
        val bytes = MessageDigest
            .getInstance("SHA-256")
            .digest(password.toByteArray())
        return bytes.joinToString("") { "%02x".format(it) }
    }


    fun limpiarToken(token: String): String {
        return token.removePrefix("Bearer ").trim()
    }

    fun obtenerUbicacion(token: String?): UbicacionResponse? {

        if(token ==null ) return null;
        val tokenLimpio = limpiarToken(token)
        val usuarioEncontrado = findByToken(tokenLimpio) ?: return null

        if(usuarioEncontrado.latitud == null || usuarioEncontrado.longitud == null) return  null

        return usuarioEncontrado.toUbicacionResponse()
    }

}