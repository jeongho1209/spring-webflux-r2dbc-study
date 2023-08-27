package com.example.springwebflux.common.error

class UnAuthorizedException(
    errorMessage: String
) : BaseException(errorMessage, 401) {
    companion object {
        const val UN_AUTHORIZED = "Un Authorized"
    }
}
