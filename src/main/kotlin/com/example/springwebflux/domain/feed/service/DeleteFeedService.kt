package com.example.springwebflux.domain.feed.service

import com.example.springwebflux.common.security.SecurityFacade
import com.example.springwebflux.domain.feed.domain.repository.FeedRepository
import com.example.springwebflux.domain.feed.exception.FeedNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class DeleteFeedService(
    private val securityFacade: SecurityFacade,
    private val feedRepository: FeedRepository,
) {

    @Transactional
    suspend fun deleteFeed(feedId: UUID) {
        val feed = feedRepository.findById(feedId)
            ?: throw FeedNotFoundException(FeedNotFoundException.FEED_NOT_FOUND_EXCEPTION)

        securityFacade.validateUserId(
            targetUserId = feed.userId,
            currentUserId = securityFacade.getCurrentUser().id,
        )

        feedRepository.delete(feed)
    }
}
