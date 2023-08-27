package com.example.springwebflux.domain.feed.domain

import com.example.springwebflux.common.entity.BaseUUIDEntity
import org.springframework.data.relational.core.mapping.Table
import java.util.UUID

@Table("tbl_feed")
class FeedEntity(
    @get:JvmName("getIdentifier")
    override var id: UUID = UUID(0, 0),
    val userId: UUID,
    val title: String,
    val content: String,
) : BaseUUIDEntity(id)
