package com.example.springwebflux.domain.feed.router.dto.response

import java.util.UUID

data class QueryFeedList(
    val feedList: List<FeedElement>,
) {
    data class FeedElement(
        val id: UUID,
        val title: String,
        val content: String,
        val name: String,
    )
}
