package com.example.springwebflux.common.error

interface ExceptionAttribute {
    val errorMessage: String
    val statusCode: Int
}

abstract class BaseException(
    override val errorMessage: String,
    override val statusCode: Int,
) : RuntimeException(errorMessage), ExceptionAttribute {
    override fun fillInStackTrace(): Throwable = this
}
