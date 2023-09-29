package com.example.springwebflux.domain.user.router

import com.example.springwebflux.domain.refreshtoken.dto.response.TokenResponse
import com.example.springwebflux.domain.user.router.dto.request.UserSignInRequest
import com.example.springwebflux.domain.user.router.dto.request.UserSignUpRequest
import com.example.springwebflux.domain.user.service.QueryUserInfoService
import com.example.springwebflux.domain.user.service.UserSignInService
import com.example.springwebflux.domain.user.service.UserSignUpService
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyToMono
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import java.net.URI

@Component
class UserHandler(
    private val userSignUpService: UserSignUpService,
    private val userSignInService: UserSignInService,
    private val queryUserInfoService: QueryUserInfoService,
) {

    suspend fun signUp(serverRequest: ServerRequest): ServerResponse {
        val requestBody: UserSignUpRequest = serverRequest.getUserSignUpRequestBody()
        val tokenResponse: TokenResponse = userSignUpService.signUp(requestBody)
        return ServerResponse.created(URI("/users")).bodyValueAndAwait(tokenResponse)
    }

    private suspend fun ServerRequest.getUserSignUpRequestBody() =
        this.bodyToMono<UserSignUpRequest>().awaitSingle()

    suspend fun signIn(serverRequest: ServerRequest): ServerResponse {
        val requestBody: UserSignInRequest = serverRequest.getUserSignInRequestBody()
        val tokenResponse: TokenResponse = userSignInService.signIn(requestBody)
        return ServerResponse.ok().bodyValueAndAwait(tokenResponse)
    }

    private suspend fun ServerRequest.getUserSignInRequestBody() =
        this.bodyToMono<UserSignInRequest>().awaitSingle()

    suspend fun getUserInfo(serverRequest: ServerRequest): ServerResponse {
        val userInfoResponse = queryUserInfoService.getUserInfo()
        return ServerResponse.ok().bodyValueAndAwait(userInfoResponse)
    }
}
