package com.example.springwebflux.domain.refreshtoken.exception

import com.example.springwebflux.common.error.BaseException

class RefreshTokenSaveFailedException(
    errorMessage: String,
) : BaseException(errorMessage, 500) {
    companion object {
        const val REFRESH_TOKEN_SAVE_FAILED = "Refresh Token Save Failed"
    }
}
