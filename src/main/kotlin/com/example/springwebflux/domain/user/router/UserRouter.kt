package com.example.springwebflux.domain.user.router

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class UserRouter {

    @Bean
    fun userBaseRouter(userHandler: UserHandler) = coRouter {
        "/users".nest {
            contentType(MediaType.APPLICATION_JSON)
            POST("/signup", userHandler::signUp)
            POST("/signIn", userHandler::signIn)
        }
    }
}
