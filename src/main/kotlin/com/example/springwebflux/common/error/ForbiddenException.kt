package com.example.springwebflux.common.error

class ForbiddenException(
    errorMessage: String
) : BaseException(errorMessage, 403) {
    companion object {
        const val FORBIDDEN_EXCEPTION = "ForBidden Exception"
    }
}
