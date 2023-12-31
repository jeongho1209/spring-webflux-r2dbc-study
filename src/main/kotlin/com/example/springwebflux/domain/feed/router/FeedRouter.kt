package com.example.springwebflux.domain.feed.router

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class FeedRouter {

    @Bean
    fun feedBaseRouter(feedHandler: FeedHandler) = coRouter {
        "/feeds".nest {
            contentType(MediaType.APPLICATION_JSON)
            POST("", feedHandler::createFeed)
            GET("", feedHandler::getFeedList)
            DELETE("/{feedId}", feedHandler::deleteFeed)
            PUT("/{feedId}", feedHandler::updateFeed)
        }
    }
}
