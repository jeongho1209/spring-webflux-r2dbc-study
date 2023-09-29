package com.example.springwebflux.domain.feed.service

import com.example.springwebflux.common.security.SecurityFacade
import com.example.springwebflux.domain.feed.domain.repository.FeedRepository
import com.example.springwebflux.domain.feed.exception.FeedNotFoundException
import com.example.springwebflux.domain.feed.router.dto.request.UpdateFeedRequest
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class UpdateFeedService(
    private val securityFacade: SecurityFacade,
    private val feedRepository: FeedRepository,
) {

    suspend fun updateFeed(feedId: UUID, request: UpdateFeedRequest) {
        val user = securityFacade.getCurrentUser()
        val feed = feedRepository.findById(feedId)
            ?: throw FeedNotFoundException(FeedNotFoundException.FEED_NOT_FOUND_EXCEPTION)

        user.validateUserId(
            targetUserId = feed.userId,
            currentUserId = user.id,
        )

        feedRepository.save(
            feed.apply { updateFeed(request.title, request.content) }
        )
    }
}
