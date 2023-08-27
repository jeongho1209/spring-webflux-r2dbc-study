package com.example.springwebflux.common.error.handler

import com.example.springwebflux.common.error.BaseException
import com.example.springwebflux.common.error.ExceptionAttribute
import com.example.springwebflux.common.error.InternalServerErrorException
import com.example.springwebflux.common.error.RequestHandlerNotFoundException
import com.example.springwebflux.common.error.response.BindErrorResponse
import com.example.springwebflux.common.error.response.ErrorResponse
import org.springframework.boot.autoconfigure.web.WebProperties
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler
import org.springframework.boot.web.reactive.error.ErrorAttributes
import org.springframework.context.ApplicationContext
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.codec.ServerCodecConfigurer
import org.springframework.stereotype.Component
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import org.springframework.web.bind.support.WebExchangeBindException
import org.springframework.web.reactive.function.server.RequestPredicates
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.server.ResponseStatusException
import reactor.core.publisher.Mono

@Order(-2)
@Component
class ErrorWebExceptionHandler(
    errorAttributes: ErrorAttributes,
    webProperties: WebProperties,
    applicationContext: ApplicationContext,
    serverCodecConfigurer: ServerCodecConfigurer,
) : AbstractErrorWebExceptionHandler(
    errorAttributes,
    webProperties.resources,
    applicationContext,
) {

    init {
        super.setMessageReaders(serverCodecConfigurer.readers)
        super.setMessageWriters(serverCodecConfigurer.writers)
    }

    override fun getRoutingFunction(errorAttributes: ErrorAttributes): RouterFunction<ServerResponse> =
        RouterFunctions.route(RequestPredicates.all(), this::handleError)

    private fun handleError(request: ServerRequest): Mono<ServerResponse> =
        when (val e = super.getError(request)) {
            is BaseException -> e.toErrorResponse()
            is WebExchangeBindException -> e.getBindErrorMessage()
            is ResponseStatusException -> RequestHandlerNotFoundException(RequestHandlerNotFoundException.REQUEST_HANDLER_NOT_FOUND).toErrorResponse()
            else -> {
                e.printStackTrace()
                InternalServerErrorException(InternalServerErrorException.UNEXPECTED_EXCEPTION).toErrorResponse()
            }
        }

    private fun BindingResult.getBindErrorMessage(): Mono<ServerResponse> {
        val errorMap = HashMap<String, String?>()

        for (error: FieldError in this.fieldErrors) {
            errorMap[error.field] = error.defaultMessage
        }

        val errorStatus = HttpStatus.BAD_REQUEST

        return ServerResponse.status(errorStatus)
            .bodyValue(
                BindErrorResponse(
                    statusCode = errorStatus.value(),
                    fieldError = errorMap,
                )
            )
    }

    private fun ExceptionAttribute.toErrorResponse() =
        ServerResponse.status(this.statusCode)
            .bodyValue(
                ErrorResponse(
                    errorMessage = this.errorMessage,
                    responseStatus = this.statusCode,
                ),
            )
}
