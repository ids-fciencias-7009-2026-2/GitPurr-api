package com.gitpurr.ids.sistemaAdopcion.repositories

import com.gitpurr.ids.sistemaAdopcion.entities.AnimalitoEntity
import org.springframework.data.repository.CrudRepository

interface AnimalitoRepository : CrudRepository<AnimalitoEntity, Long> {
    fun findByUsuarioId(usuarioId: Long): List<AnimalitoEntity>
}
