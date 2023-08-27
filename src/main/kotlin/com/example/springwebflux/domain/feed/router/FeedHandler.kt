package com.example.springwebflux.domain.feed.router

import com.example.springwebflux.domain.feed.router.dto.request.CreateFeedRequest
import com.example.springwebflux.domain.feed.service.CreateFeedService
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.buildAndAwait
import java.net.URI

@Component
class FeedHandler(
    private val createFeedService: CreateFeedService,
) {

    suspend fun createFeed(serverRequest: ServerRequest): ServerResponse {
        val requestBody = serverRequest.getCreateFeedRequestBody()
        createFeedService.createFeed(requestBody)
        return ServerResponse.created(URI("/feeds")).buildAndAwait()
    }

    private suspend fun ServerRequest.getCreateFeedRequestBody() =
        this.bodyToMono(CreateFeedRequest::class.java).awaitSingle()
}
