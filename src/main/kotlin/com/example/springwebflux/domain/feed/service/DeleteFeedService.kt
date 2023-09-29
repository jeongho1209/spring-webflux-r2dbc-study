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
        val user = securityFacade.getCurrentUser()
        val feed = feedRepository.findById(feedId)
            ?: throw FeedNotFoundException(FeedNotFoundException.FEED_NOT_FOUND_EXCEPTION)

        user.validateUserId(
            targetUserId = feed.userId,
            currentUserId = user.id,
        )

        feedRepository.delete(feed)
    }
}
