package com.example.springwebflux.domain.feed.service

import com.example.springwebflux.common.security.SecurityFacade
import com.example.springwebflux.domain.feed.domain.FeedEntity
import com.example.springwebflux.domain.feed.domain.repository.FeedRepository
import com.example.springwebflux.domain.feed.router.dto.request.CreateFeedRequest
import org.springframework.stereotype.Service

@Service
class CreateFeedService(
    private val feedRepository: FeedRepository,
    private val securityFacade: SecurityFacade,
) {

    suspend fun createFeed(request: CreateFeedRequest) {
        feedRepository.save(
            FeedEntity(
                userId = securityFacade.getCurrentUser().id,
                title = request.title,
                content = request.content
            )
        )
    }
}
