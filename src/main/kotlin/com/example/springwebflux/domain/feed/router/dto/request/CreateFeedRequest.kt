package com.example.springwebflux.domain.feed.router.dto.request

data class CreateFeedRequest(
    val title: String,
    val content: String,
)
