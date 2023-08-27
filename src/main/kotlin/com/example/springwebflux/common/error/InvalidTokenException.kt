package com.example.springwebflux.common.error

class InvalidTokenException(
    errorMessage: String,
) : BaseException(errorMessage, 401){
    companion object {
        const val INVALID_TOKEN = "Invalid Token"
    }
}
