package com.example.springwebflux.common.security.jwt

import com.example.springwebflux.common.security.SecurityProperties
import com.example.springwebflux.domain.refreshtoken.dto.response.TokenResponse
import com.example.springwebflux.domain.refreshtoken.exception.RefreshTokenSaveFailedException
import io.jsonwebtoken.Header.JWT_TYPE
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.data.redis.core.ReactiveRedisOperations
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.Date
import java.util.UUID

@Component
class JwtProvider(
    private val reactiveRedisOperations: ReactiveRedisOperations<String, Any>,
    private val securityProperties: SecurityProperties,
) {

    suspend fun getToken(userId: UUID) = TokenResponse(
        accessToken = createAccessToken(userId),
        refreshToken = createRefreshToken(userId),
        accessTokenExpiredAt = nowPlusSecond(securityProperties.accessExp),
        refreshTokenExpiredAt = nowPlusSecond(securityProperties.refreshExp),
    )

    private fun nowPlusSecond(tokenExpiredAt: Long) =
        LocalDateTime.now().withNano(0).plusSeconds(tokenExpiredAt)

    fun createAccessToken(userId: UUID): String =
        createToken(userId, JwtProperty.ACCESS, securityProperties.accessExp)

    suspend fun createRefreshToken(userId: UUID): String {
        val refreshToken = createToken(userId, JwtProperty.REFRESH, securityProperties.refreshExp)
        return saveRefreshToken(refreshToken)
    }

    private fun createToken(userId: UUID, tokenType: String, tokenExpiredAt: Long) =
        Jwts.builder()
            .signWith(SignatureAlgorithm.HS256, securityProperties.secretKey)
            .setSubject(userId.toString())
            .setHeaderParam(JWT_TYPE, tokenType)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + tokenExpiredAt * 1000))
            .compact()

    private suspend fun saveRefreshToken(refreshToken: String): String {
        val isSaveSuccess = reactiveRedisOperations.opsForValue()
            .set(refreshToken, securityProperties.refreshExp).awaitSingle()

        if (!isSaveSuccess) {
            throw RefreshTokenSaveFailedException(RefreshTokenSaveFailedException.REFRESH_TOKEN_SAVE_FAILED)
        }

        return refreshToken
    }
}
