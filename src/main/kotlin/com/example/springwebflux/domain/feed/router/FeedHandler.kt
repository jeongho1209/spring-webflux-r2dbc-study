package com.example.springwebflux.domain.feed.router

import com.example.springwebflux.domain.feed.router.dto.request.CreateFeedRequest
import com.example.springwebflux.domain.feed.router.dto.response.QueryFeedList
import com.example.springwebflux.domain.feed.service.CreateFeedService
import com.example.springwebflux.domain.feed.service.DeleteFeedService
import com.example.springwebflux.domain.feed.service.QueryFeedListService
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.buildAndAwait
import java.net.URI
import java.util.UUID

@Component
class FeedHandler(
    private val createFeedService: CreateFeedService,
    private val queryFeedListService: QueryFeedListService,
    private val deleteFeedService: DeleteFeedService,
) {

    suspend fun createFeed(serverRequest: ServerRequest): ServerResponse {
        val requestBody = serverRequest.getCreateFeedRequestBody()
        createFeedService.createFeed(requestBody)
        return ServerResponse.created(URI("/feeds")).buildAndAwait()
    }

    private suspend fun ServerRequest.getCreateFeedRequestBody() =
        this.bodyToMono(CreateFeedRequest::class.java).awaitSingle()

    suspend fun getFeedList(serverRequest: ServerRequest): ServerResponse {
        val feedList: QueryFeedList = queryFeedListService.getFeedList()
        return ServerResponse.ok().bodyValueAndAwait(feedList)
    }

    suspend fun deleteFeed(serverRequest: ServerRequest): ServerResponse {
        val feedId = serverRequest.pathVariable("feedId")
        deleteFeedService.deleteFeed(UUID.fromString(feedId))
        return ServerResponse.noContent().buildAndAwait()
    }
}
