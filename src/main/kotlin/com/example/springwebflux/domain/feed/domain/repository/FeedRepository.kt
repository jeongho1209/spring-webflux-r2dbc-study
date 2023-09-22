package com.example.springwebflux.domain.feed.domain.repository

import com.example.springwebflux.domain.feed.domain.FeedEntity
import com.example.springwebflux.domain.feed.router.dto.response.QueryFeedList.FeedElement
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import reactor.core.publisher.Flux
import java.util.UUID

interface FeedRepository : CoroutineCrudRepository<FeedEntity, UUID> {
    @Query(
        """
            select 
            f.id,
            f.title,
            f.content,
            u.name
            from tbl_feed as f
            inner join tbl_user as u 
                on f.user_id = u.id
        """
    )
    suspend fun queryAllFeed(): Flux<FeedElement>
}
