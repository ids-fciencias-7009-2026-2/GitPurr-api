package com.gitpurr.ids.sistemaAdopcion.controllers

import com.gitpurr.ids.sistemaAdopcion.domain.toAnimalito
import com.gitpurr.ids.sistemaAdopcion.domain.toAnimalitoResponse
import com.gitpurr.ids.sistemaAdopcion.dto.request.CreateAnimalitoRequest
import com.gitpurr.ids.sistemaAdopcion.services.AnimalitoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/animalitos")
class AnimalitoController {

    @Autowired
    lateinit var animalitoService: AnimalitoService

    @PostMapping
    fun registrarAnimalito(
        @RequestHeader("Authorization", required = false) token: String?,
        @RequestBody request: CreateAnimalitoRequest
    ): ResponseEntity<Any> {

        if (token == null) {
            return ResponseEntity.status(401).build()
        }

        val soloToken = token.removePrefix("Bearer ").trim()

        if (
            request.nombre.isBlank() ||
            request.edad.isBlank() ||
            request.especie.isBlank() ||
            request.sexo.isBlank() ||
            request.tamano.isBlank() ||
            request.codigoPostal.isBlank()
        ) {
            return ResponseEntity.badRequest().body("Faltan campos obligatorios")
        }

        val animalitoNuevo = request.toAnimalito()
        val guardado = animalitoService.registrarAnimalito(soloToken, animalitoNuevo)

        return if (guardado == null) {
            ResponseEntity.status(401).build()
        } else {
            ResponseEntity.ok(guardado.toAnimalitoResponse())
        }
    }

    @GetMapping
    fun listarAnimalitos(): ResponseEntity<Any> {
        val animalitos = animalitoService
            .listarAnimalitos()
            .map { it.toAnimalitoResponse() }

        return ResponseEntity.ok(animalitos)
    }
}