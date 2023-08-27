package com.example.springwebflux.common.error

class InternalServerErrorException(
    errorMessage: String,
) : BaseException(errorMessage, 500) {
    companion object {
        const val UNEXPECTED_EXCEPTION = "Unexpected Error Occurred"
    }
}
