package com.example.springwebflux.domain.feed.domain.repository

import com.example.springwebflux.domain.feed.domain.FeedEntity
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import java.util.UUID

interface FeedRepository : CoroutineCrudRepository<FeedEntity, UUID>
