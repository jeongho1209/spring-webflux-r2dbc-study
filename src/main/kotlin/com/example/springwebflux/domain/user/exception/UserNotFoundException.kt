package com.example.springwebflux.domain.user.exception

import com.example.springwebflux.common.error.BaseException

class UserNotFoundException(
    errorMessage: String,
) : BaseException(errorMessage, 404) {
    companion object {
        const val USER_NOT_FOUND = "User Not Found"
    }
}
