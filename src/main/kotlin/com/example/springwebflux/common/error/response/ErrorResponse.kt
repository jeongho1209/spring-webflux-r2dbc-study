package com.example.springwebflux.common.error.response

data class ErrorResponse(
    val errorMessage: String,
    val responseStatus: Int,
)
