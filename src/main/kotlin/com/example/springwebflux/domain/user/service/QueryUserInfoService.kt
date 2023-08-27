package com.example.springwebflux.domain.user.service

import com.example.springwebflux.common.security.SecurityFacade
import com.example.springwebflux.domain.user.router.dto.response.QueryUserInfoResponse
import org.springframework.stereotype.Service

@Service
class QueryUserInfoService(
    private val securityFacade: SecurityFacade,
) {

    suspend fun getUserInfo(): QueryUserInfoResponse {
        val user = securityFacade.getCurrentUser()

        return QueryUserInfoResponse(
            accountId = user.accountId,
            name = user.name,
        )
    }
}
