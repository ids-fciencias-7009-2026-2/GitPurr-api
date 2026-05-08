package com.gitpurr.ids.sistemaAdopcion.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class GeocodioConfig {

    @Bean
    fun geocodioRestTemplate(): RestTemplate {
        return RestTemplate()
    }
}
