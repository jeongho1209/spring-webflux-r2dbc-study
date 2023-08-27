package com.example.springwebflux.common.security.auth

import com.example.springwebflux.domain.user.domain.repository.UserRepository
import com.example.springwebflux.domain.user.exception.UserNotFoundException
import kotlinx.coroutines.reactor.mono
import org.springframework.security.core.userdetails.ReactiveUserDetailsService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import java.util.UUID

@Component
class AuthDetailsService(
    private val userRepository: UserRepository,
) : ReactiveUserDetailsService {

    override fun findByUsername(userId: String?): Mono<UserDetails> = mono {
        val user = userRepository.findById(UUID.fromString(userId))
            ?: throw UserNotFoundException(UserNotFoundException.USER_NOT_FOUND)
        return@mono AuthDetails(user.id)
    }
}
