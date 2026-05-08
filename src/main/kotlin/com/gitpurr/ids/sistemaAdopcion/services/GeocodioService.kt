package com.gitpurr.ids.sistemaAdopcion.services

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class GeocodioService (
    private val geocodioRestTemplate: RestTemplate,
    @Value("\${geocodio.api.key}") private val apiKey: String
) {
    fun obtenerCoordenadas(codigoPostal: String) : Pair<Double, Double> {

        val url = "https://api.geocod.io/v1.12/geocode?q=$codigoPostal&country=Mexico&api_key=$apiKey"

        val response = geocodioRestTemplate.getForObject(url,Map::class.java)?: throw RuntimeException("Geocodio no respondió")

        val results = response["results"] as? List<*>

        if(results.isNullOrEmpty()) throw RuntimeException("Geocodio no encontro el código postal: $codigoPostal")

        val first = results[0] as Map<*, *>

        val location = first["location"] as Map<*, *>

        val lat = location["lat"] as Double
        val long = location["lng"] as Double

        return Pair(lat, long)
    }
}