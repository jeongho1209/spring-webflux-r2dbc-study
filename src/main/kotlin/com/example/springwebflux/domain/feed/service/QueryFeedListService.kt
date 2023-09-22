package com.example.springwebflux.domain.feed.service

import com.example.springwebflux.domain.feed.domain.repository.FeedRepository
import com.example.springwebflux.domain.feed.router.dto.response.QueryFeedList
import com.example.springwebflux.domain.feed.router.dto.response.QueryFeedList.FeedElement
import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.stereotype.Service

@Service
class QueryFeedListService(
    private val feedRepository: FeedRepository,
) {

    suspend fun getFeedList(): QueryFeedList {
        val feedList = feedRepository.queryAllFeed().collectList().awaitSingle()
        val response = feedList.map { it.toFeedElement() }

        return QueryFeedList(response)
    }

    private fun FeedElement.toFeedElement() = FeedElement(
        id = this.id,
        title = this.title,
        content = this.content,
        name = this.name,
    )
}
