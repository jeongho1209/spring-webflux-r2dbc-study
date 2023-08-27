package com.example.springwebflux.common.security.jwt

import com.example.springwebflux.common.error.ExpiredTokenException
import com.example.springwebflux.common.error.InternalServerErrorException
import com.example.springwebflux.common.error.InvalidTokenException
import com.example.springwebflux.common.security.SecurityProperties
import com.example.springwebflux.common.security.auth.AuthDetailsService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.InvalidClaimException
import io.jsonwebtoken.Jwts
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

@Component
class JwtParser(
    private val securityProperties: SecurityProperties,
    private val authDetailsService: AuthDetailsService,
) {

    suspend fun getAuthentication(token: String): Authentication {
        val claims = getClaims(token)

        val authDetails = authDetailsService.findByUsername(claims.subject).awaitSingle()
        return UsernamePasswordAuthenticationToken(authDetails, "", authDetails.authorities)
    }

    private fun getClaims(token: String): Claims =
        try {
            Jwts.parser().setSigningKey(securityProperties.secretKey)
                .parseClaimsJws(token).body
        } catch (e: Exception) {
            when (e) {
                is InvalidClaimException -> throw InvalidTokenException(InvalidTokenException.INVALID_TOKEN)
                is ExpiredJwtException -> throw ExpiredTokenException(ExpiredTokenException.EXPIRED_TOKEN)
                else -> throw InternalServerErrorException(InternalServerErrorException.UNEXPECTED_EXCEPTION)
            }
        }
}
