package com.gitpurr.ids.sistemaAdopcion.services

import com.gitpurr.ids.sistemaAdopcion.domain.Animalito
import com.gitpurr.ids.sistemaAdopcion.domain.toAnimalito
import com.gitpurr.ids.sistemaAdopcion.repositories.AnimalitoRepository
import com.gitpurr.ids.sistemaAdopcion.repositories.UsuarioRepository
import com.gitpurr.ids.sistemaAdopcion.repositories.toAnimalitoEntity
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
}