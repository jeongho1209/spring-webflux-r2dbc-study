package com.example.springwebflux.common.error.response

data class BindErrorResponse(
    val statusCode: Int,
    val fieldError: HashMap<String, String?>,
)
