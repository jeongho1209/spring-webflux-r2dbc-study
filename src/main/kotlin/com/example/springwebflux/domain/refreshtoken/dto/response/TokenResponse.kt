package com.example.springwebflux.domain.refreshtoken.dto.response

import java.time.LocalDateTime

data class TokenResponse(
    val accessToken: String,
    val refreshToken: String,
    val accessTokenExpiredAt: LocalDateTime,
    val refreshTokenExpiredAt: LocalDateTime,
)
