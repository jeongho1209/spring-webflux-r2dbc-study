package com.example.springwebflux.domain.user.router.dto.request

import org.jetbrains.annotations.NotNull

data class UserSignUpRequest(
    @field:NotNull
    val accountId: String,
    @field:NotNull
    val password: String,
    @field:NotNull
    val name: String,
)
