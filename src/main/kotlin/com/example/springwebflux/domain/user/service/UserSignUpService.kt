package com.example.springwebflux.domain.user.service

import com.example.springwebflux.common.security.jwt.JwtProvider
import com.example.springwebflux.domain.refreshtoken.dto.response.TokenResponse
import com.example.springwebflux.domain.user.domain.UserEntity
import com.example.springwebflux.domain.user.domain.repository.UserRepository
import com.example.springwebflux.domain.user.exception.UserExistException
import com.example.springwebflux.domain.user.router.dto.request.UserSignUpRequest
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserSignUpService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtProvider: JwtProvider,
) {

    suspend fun signUp(request: UserSignUpRequest): TokenResponse {
        if (userRepository.existsByAccountId(request.accountId)) {
            throw UserExistException(UserExistException.USER_EXIST)
        }

        val user = userRepository.save(
            UserEntity(
                accountId = request.accountId,
                password = passwordEncoder.encode(request.password),
                name = request.name,
            )
        )

        return jwtProvider.getToken(user.id)
    }
}
