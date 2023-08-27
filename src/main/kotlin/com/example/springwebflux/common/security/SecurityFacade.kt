package com.example.springwebflux.common.security

import com.example.springwebflux.domain.user.domain.UserEntity
import com.example.springwebflux.domain.user.domain.repository.UserRepository
import com.example.springwebflux.domain.user.exception.UserNotFoundException
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class SecurityFacade(
    private val userRepository: UserRepository,
) {

    suspend fun getCurrentUser(): UserEntity {
        val userId = ReactiveSecurityContextHolder.getContext().awaitSingle().authentication?.name
        return userRepository.findById(UUID.fromString(userId))
            ?: throw UserNotFoundException(UserNotFoundException.USER_NOT_FOUND)
    }
}
