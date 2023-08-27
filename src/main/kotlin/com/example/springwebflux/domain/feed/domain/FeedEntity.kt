package com.example.springwebflux.domain.feed.domain

import org.springframework.data.relational.core.mapping.Table
import java.util.UUID

@Table("tbl_feed")
class FeedEntity(
    val id: UUID = UUID.randomUUID(),
    val userId: UUID,
    val title: String,
    val content: String,
)
