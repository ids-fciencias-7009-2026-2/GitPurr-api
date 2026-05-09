package com.gitpurr.ids.sistemaAdopcion.services

import com.gitpurr.ids.sistemaAdopcion.domain.Animalito
import com.gitpurr.ids.sistemaAdopcion.domain.toAnimalito
import com.gitpurr.ids.sistemaAdopcion.domain.toAnimalitoResponse
import com.gitpurr.ids.sistemaAdopcion.dto.response.AnimalitoResponse
import com.gitpurr.ids.sistemaAdopcion.repositories.AnimalitoRepository
import com.gitpurr.ids.sistemaAdopcion.repositories.UsuarioRepository
import com.gitpurr.ids.sistemaAdopcion.repositories.toAnimalitoEntity
import com.gitpurr.ids.sistemaAdopcion.adapters.EmailAdapter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AnimalitoService {

    @Autowired
    lateinit var animalitoRepository: AnimalitoRepository
    @Autowired
    lateinit var geocodioService: GeocodioService

    @Autowired
    lateinit var usuarioRepository: UsuarioRepository

    @Autowired
    lateinit var emailAdapter: EmailAdapter

    fun registrarAnimalito(token: String, animalito: Animalito): Animalito? {
        val usuarioEntity = usuarioRepository.findByToken(token) ?: return null

        val (lat,lng) = geocodioService.obtenerCoordenadas(animalito.codigoPostal)
        animalito.latitudAprox = lat
        animalito.longitudAprox = lng
        val animalitoEntity = animalito.toAnimalitoEntity(usuarioEntity)
        val guardado = animalitoRepository.save(animalitoEntity)

        return guardado.toAnimalito()
    }

    fun listarAnimalitos(): List<Animalito> {
        return animalitoRepository.findAll().map { it.toAnimalito() }
    }

    fun obtenerAnimalitoPorId(id: Long): Animalito? {
        val entity = animalitoRepository.findById(id)
        return if (entity.isPresent) entity.get().toAnimalito() else null
    }

    fun limpiarToken(token: String): String {
        return token.removePrefix("Bearer ").trim()
    }

    fun buscarAnimalitosCercanos(token: String?): List<AnimalitoResponse>? {
        if(token == null) return null

        val usuarioEntity = usuarioRepository.findByToken(limpiarToken(token)) ?: return null
        val lat = usuarioEntity.latitud ?: return null
        val lng = usuarioEntity.longitud ?: return null

        val radioKm = 30.0

        val delta = radioKm/111.0

        val latMin = lat - delta
        val latMax = lat + delta
        val lngMin = lng - delta
        val lngMax = lng + delta

        return  animalitoRepository.buscarCercanos(latMin,lngMin,latMax, lngMax).map { it.toAnimalito().toAnimalitoResponse() }

    }

    fun expresarInteres(token: String, animalitoId: Long): Result<Any> {
        val interesado = usuarioRepository.findByToken(token) ?: return Result.failure(Exception("No autorizado"))
        val animalito = animalitoRepository.findById(animalitoId).orElse(null) ?: return Result.failure(Exception("Animalito no encontrado"))
        val dueno = animalito.usuario ?: return Result.failure(Exception("Sin dueño"))

        val htmlTemplate = """
    <div style="font-family: Arial, sans-serif; border: 1px solid #ccc; padding: 20px;">
        <h1 style="color: #e07b39;">GitPurr 🐾</h1>
        <p>Hola, <strong>${dueno.nombre}</strong>,</p>
        <p>Alguien está interesado en adoptar a <strong>${animalito.nombre}</strong></p>
        <p>Puedes contactar a <strong>${interesado.nombre}</strong> en: ${interesado.email}</p>
        <hr>
        <footer style="font-size: 0.8em; color: #777;">
            Este es un correo automático, por favor no responder.
        </footer>
    </div>
""".trimIndent()

        val subject = "¡Alguien quiere adoptar a ${animalito.nombre}!"
        return emailAdapter.sendHtmlEmail(dueno.email, subject, htmlTemplate)
    }

}