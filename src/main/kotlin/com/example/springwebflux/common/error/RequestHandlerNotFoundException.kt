package com.example.springwebflux.common.error

class RequestHandlerNotFoundException(
    errorMessage: String
) : BaseException(errorMessage, 404) {
    companion object {
        const val REQUEST_HANDLER_NOT_FOUND = "Request Handler Not Found"
    }
}
