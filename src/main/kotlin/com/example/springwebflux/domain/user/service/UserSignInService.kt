package com.example.springwebflux.domain.user.service

import com.example.springwebflux.common.error.UnAuthorizedException
import com.example.springwebflux.common.security.jwt.JwtProvider
import com.example.springwebflux.domain.refreshtoken.dto.response.TokenResponse
import com.example.springwebflux.domain.user.domain.repository.UserRepository
import com.example.springwebflux.domain.user.router.dto.request.UserSignInRequest
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserSignInService(
    private val jwtProvider: JwtProvider,
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
) {

    suspend fun signIn(request: UserSignInRequest): TokenResponse {
        val user = userRepository.findByAccountId(request.accountId)
            ?: throw UnAuthorizedException(UnAuthorizedException.UN_AUTHORIZED)

        checkIsPasswordMatches(request.password, user.password)

        return jwtProvider.getToken(user.id)
    }

    private fun checkIsPasswordMatches(
        rawPassword: String,
        encodePassword: String,
    ) {
        val isPasswordMatches = passwordEncoder.matches(rawPassword, encodePassword)
        if (!isPasswordMatches) {
            throw UnAuthorizedException(UnAuthorizedException.UN_AUTHORIZED)
        }
    }
}
