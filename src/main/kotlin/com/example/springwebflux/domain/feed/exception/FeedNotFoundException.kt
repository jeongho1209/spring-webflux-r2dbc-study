package com.example.springwebflux.domain.feed.exception

import com.example.springwebflux.common.error.BaseException

class FeedNotFoundException(
    errorMessage: String,
) : BaseException(errorMessage, 404) {
    companion object {
        const val FEED_NOT_FOUND_EXCEPTION = "Feed Not Found"
    }
}
