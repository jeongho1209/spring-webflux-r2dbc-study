package com.example.springwebflux.common.error

class ExpiredTokenException(
    errorMessage: String
) : BaseException(errorMessage, 401) {
    companion object {
        const val EXPIRED_TOKEN = "Expired Token"
    }
}
