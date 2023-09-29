package com.example.springwebflux.domain.feed.domain

import com.example.springwebflux.common.entity.BaseUUIDEntity
import org.springframework.data.relational.core.mapping.Table
import java.util.UUID

@Table("tbl_feed")
class FeedEntity(
    @get:JvmName("getIdentifier")
    override var id: UUID = UUID(0, 0),
    val userId: UUID,
    title: String,
    content: String,
) : BaseUUIDEntity(id) {
    var title = title
        private set
    var content = content
        private set

    fun updateFeed(title: String, content: String): FeedEntity {
        this.title = title
        this.content = content
        return this
    }
}
