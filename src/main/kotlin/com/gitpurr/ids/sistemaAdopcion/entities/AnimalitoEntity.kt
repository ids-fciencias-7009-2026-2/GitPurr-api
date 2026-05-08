package com.gitpurr.ids.sistemaAdopcion.entities

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "animalito")
data class AnimalitoEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    var nombre: String = "",
    var edad: String = "",
    var especie: String = "",
    var raza: String? = null,
    var sexo: String = "",
    var tamano: String = "",
    var descripcion: String? = null,

    @Column(name = "codigo_postal")
    var codigoPostal: String = "",

    @Column(name = "latitud_aprox")
    var latitudAprox: Double? = null,

    @Column(name = "longitud_aprox")
    var longitudAprox: Double? = null,

    @Column(name = "foto_url")
    var fotoUrl: String? = null,

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    var usuario: UsuarioEntity? = null,

    @Column(name = "created_at")
    var createdAt: LocalDateTime? = null,

    @Column(name = "updated_at")
    var updatedAt: LocalDateTime? = null,

    @Column(name = "deleted_at")
    var deletedAt: LocalDateTime? = null
) {
    @PrePersist
    fun prePersist() {
        val now = LocalDateTime.now()
        createdAt = now
        updatedAt = now
    }

    @PreUpdate
    fun preUpdate() {
        updatedAt = LocalDateTime.now()
    }
}