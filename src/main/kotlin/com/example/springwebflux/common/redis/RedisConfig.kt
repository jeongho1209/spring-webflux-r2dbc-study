package com.example.springwebflux.common.redis

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory
import org.springframework.data.redis.core.ReactiveRedisOperations
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
class RedisConfig {

    @Bean
    fun redisOperations(
        factory: ReactiveRedisConnectionFactory,
        objectMapper: ObjectMapper
    ): ReactiveRedisOperations<String, Any> {
        val stringSerializer = StringRedisSerializer()
        val serializationContext = RedisSerializationContext.newSerializationContext<String, Any>()
            .key(stringSerializer)
            .value(GenericJackson2JsonRedisSerializer(objectMapper))
            .hashKey(stringSerializer)
            .hashValue(stringSerializer)
            .build()

        return ReactiveRedisTemplate(factory, serializationContext)
    }
}
