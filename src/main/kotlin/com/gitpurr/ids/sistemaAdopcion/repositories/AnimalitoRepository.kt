package com.gitpurr.ids.sistemaAdopcion.repositories

import com.gitpurr.ids.sistemaAdopcion.entities.AnimalitoEntity
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface AnimalitoRepository : CrudRepository<AnimalitoEntity, Long> {
    fun findByUsuarioId(usuarioId: Long): List<AnimalitoEntity>

    @Query(
        "SELECT a FROM AnimalitoEntity a WHERE a.latitudAprox BETWEEN :latMin AND :latMax " +
                "AND a.longitudAprox BETWEEN :lngMin AND :lngMax"
    )
    fun buscarCercanos(latMin: Double, lngMin: Double, latMax: Double, lngMax: Double): List<AnimalitoEntity>

}
