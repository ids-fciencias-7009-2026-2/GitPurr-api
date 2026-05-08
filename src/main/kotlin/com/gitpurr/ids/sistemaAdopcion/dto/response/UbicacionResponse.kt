package com.gitpurr.ids.sistemaAdopcion.dto.response

/**
 * DTO encargado de enviar la latitud y longitud de
 * la ubicacion de una entidad correspondiente.
 *
 * Esta clase comunica al frontend las coordenadas
 * para poder mostrar en el mapa.
 */
data class UbicacionResponse(
    val lat : Double,
    val lng : Double,
)