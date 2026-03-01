package com.gitpurr.ids.sistemaAdopcion

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import io.github.cdimascio.dotenv.dotenv

@SpringBootApplication
class SistemaAdopcionApplication

fun main(args: Array<String>) {
	dotenv().entries().forEach {
		System.setProperty(it.key, it.value)
	}
	runApplication<SistemaAdopcionApplication>(*args)
}

