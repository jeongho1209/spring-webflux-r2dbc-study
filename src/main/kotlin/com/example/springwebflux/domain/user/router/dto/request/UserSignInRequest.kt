package com.example.springwebflux.domain.user.router.dto.request

data class UserSignInRequest(
    val accountId: String,
    val password: String,
)
