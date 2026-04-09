package com.gitpurr.ids.sistemaAdopcion.services

import com.gitpurr.ids.sistemaAdopcion.domain.Usuario
import com.gitpurr.ids.sistemaAdopcion.domain.toUsuario
import com.gitpurr.ids.sistemaAdopcion.repositories.UsuarioRepository
import com.gitpurr.ids.sistemaAdopcion.repositories.toUsuarioEntity
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UsuarioService {
    val logger = LoggerFactory.getLogger(UsuarioService::class.java)

    @Autowired
    lateinit var usuarioRepository: UsuarioRepository


    fun searchAllUsuarios(): List<Usuario> {
        val allUsers = usuarioRepository.findAll()

        logger.info("all users: ${allUsers.toString()}")
        return allUsers.map { usuarioEntity -> usuarioEntity.toUsuario() }
    }

    fun addNewUsuario(usuario: Usuario): Usuario {
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

    fun actualizarUsuario(token: String, usuarioActualizado: Usuario): Usuario? {
        val usuarioEntity = usuarioRepository.findByToken(token)

        if (usuarioEntity == null) {
            logger.warn("Usuario no encontrado para token: $token")
            return null
        }

        // Actualización parcial segura
        if (usuarioActualizado.nombre.isNotBlank()) {
            usuarioEntity.nombre = usuarioActualizado.nombre
        }

        if (usuarioActualizado.email.isNotBlank()) {
            usuarioEntity.email = usuarioActualizado.email
        }

        if (!usuarioActualizado.password.isNullOrBlank()) {
            usuarioEntity.password = usuarioActualizado.password!!
        }

        usuarioRepository.save(usuarioEntity)

        return usuarioEntity.toUsuario()
    }

}