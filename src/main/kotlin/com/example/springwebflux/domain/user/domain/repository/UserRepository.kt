package com.example.springwebflux.domain.user.domain.repository

import com.example.springwebflux.domain.user.domain.UserEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import java.util.UUID

interface UserRepository : CoroutineCrudRepository<UserEntity, UUID>
