package com.example.springwebflux.domain.user.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.util.UUID

@Table(name = "tbl_user")
class UserEntity(
    @Id
    val id: UUID = UUID.randomUUID(),
    val accountId: String,
    val password: String,
    val name: String
)
