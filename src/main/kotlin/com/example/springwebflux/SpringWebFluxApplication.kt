package com.example.springwebflux

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringWebFluxApplication

fun main(args: Array<String>) {
    runApplication<SpringWebFluxApplication>(*args)
}
