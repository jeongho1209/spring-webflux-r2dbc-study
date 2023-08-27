package com.example.springwebflux

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

internal const val BASE_PACKAGE = "com.example.springwebflux"

@SpringBootApplication
class SpringWebFluxApplication

fun main(args: Array<String>) {
    runApplication<SpringWebFluxApplication>(*args)
}
