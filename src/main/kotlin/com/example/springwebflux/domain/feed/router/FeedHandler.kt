package com.example.springwebflux.domain.feed.router

import com.example.springwebflux.domain.feed.router.dto.request.CreateFeedRequest
import com.example.springwebflux.domain.feed.router.dto.request.UpdateFeedRequest
import com.example.springwebflux.domain.feed.service.CreateFeedService
import com.example.springwebflux.domain.feed.service.DeleteFeedService
import com.example.springwebflux.domain.feed.service.QueryFeedListService
import com.example.springwebflux.domain.feed.service.UpdateFeedService
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyToMono
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.buildAndAwait
import java.net.URI
import java.util.UUID

@Component
class FeedHandler(
    private val createFeedService: CreateFeedService,
    private val queryFeedListService: QueryFeedListService,
    private val deleteFeedService: DeleteFeedService,
    private val updateFeedService: UpdateFeedService,
) {

    suspend fun createFeed(serverRequest: ServerRequest): ServerResponse {
        val requestBody = serverRequest.getCreateFeedRequestBody()
        createFeedService.createFeed(requestBody)
        return ServerResponse.created(URI("/feeds")).buildAndAwait()
    }

    private suspend fun ServerRequest.getCreateFeedRequestBody() =
        this.bodyToMono<CreateFeedRequest>().awaitSingle()

    suspend fun getFeedList(serverRequest: ServerRequest): ServerResponse {
        val feedList = queryFeedListService.getFeedList()
        return ServerResponse.ok().bodyValueAndAwait(feedList)
    }

    suspend fun deleteFeed(serverRequest: ServerRequest): ServerResponse {
        val feedId = serverRequest.pathVariable("feedId")
        deleteFeedService.deleteFeed(UUID.fromString(feedId))
        return ServerResponse.noContent().buildAndAwait()
    }

    suspend fun updateFeed(serverRequest: ServerRequest): ServerResponse {
        val feedId = serverRequest.pathVariable("feedId")
        val requestBody = serverRequest.getUpdateFeedRequestBody()
        updateFeedService.updateFeed(UUID.fromString(feedId), requestBody)
        return ServerResponse.noContent().buildAndAwait()
    }

    private suspend fun ServerRequest.getUpdateFeedRequestBody() =
        this.bodyToMono<UpdateFeedRequest>().awaitSingle()
}
