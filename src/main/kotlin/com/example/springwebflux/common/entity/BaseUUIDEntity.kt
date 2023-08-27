package com.example.springwebflux.common.entity

import org.springframework.data.annotation.Id
import org.springframework.data.domain.Persistable
import java.util.UUID

abstract class BaseUUIDEntity(
    @field:Id
    @get:JvmName("getIdentifier")
    open var id: UUID = UUID(0, 0)
) : Persistable<UUID> {

    override fun getId() = id

    override fun isNew() = (id == UUID(0, 0)).also { new ->
        if (new) id = UUID.randomUUID()
    }
}
