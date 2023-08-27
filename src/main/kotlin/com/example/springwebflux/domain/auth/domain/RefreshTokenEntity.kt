package com.example.springwebflux.domain.auth.domain

import java.util.UUID

data class RefreshTokenEntity(
    val tokenValue: String,
    val userId: UUID,
)
