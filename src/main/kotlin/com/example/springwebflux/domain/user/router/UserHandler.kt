package com.example.springwebflux.domain.user.router

import com.example.springwebflux.domain.auth.router.dto.response.TokenResponse
import com.example.springwebflux.domain.user.router.dto.request.UserSignUpRequest
import com.example.springwebflux.domain.user.service.UserSignUpService
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import java.net.URI

@Component
class UserHandler(
    private val userSignUpService: UserSignUpService,
) {

    suspend fun signUp(serverRequest: ServerRequest): ServerResponse {
        val requestBody: UserSignUpRequest = serverRequest.getUserSignUpRequestBody()
        val tokenResponse: TokenResponse = userSignUpService.signUp(requestBody)
        return ServerResponse.created(URI("/users")).bodyValueAndAwait(tokenResponse)
    }

    private suspend fun ServerRequest.getUserSignUpRequestBody() =
        this.bodyToMono(UserSignUpRequest::class.java).awaitSingle()
}
