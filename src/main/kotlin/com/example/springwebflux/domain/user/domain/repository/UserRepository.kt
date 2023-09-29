package com.example.springwebflux.domain.user.domain.repository

import com.example.springwebflux.domain.user.domain.UserEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import java.util.UUID

interface UserRepository : CoroutineCrudRepository<UserEntity, UUID> {
    suspend fun existsByAccountId(accountId: String): Boolean
    suspend fun findByAccountId(accountId: String): UserEntity?
    override suspend fun findById(id: UUID): UserEntity?
}
