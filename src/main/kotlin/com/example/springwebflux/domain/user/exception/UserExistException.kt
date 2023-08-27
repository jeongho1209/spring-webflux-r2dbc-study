package com.example.springwebflux.domain.user.exception

import com.example.springwebflux.common.error.BaseException

class UserExistException(
    errorMessage: String,
) : BaseException(errorMessage, 409) {
    companion object {
        const val USER_EXIST = "User Exist"
    }
}
