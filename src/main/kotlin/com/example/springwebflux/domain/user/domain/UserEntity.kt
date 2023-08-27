package com.example.springwebflux.domain.user.domain

import com.example.springwebflux.common.entity.BaseUUIDEntity
import org.springframework.data.relational.core.mapping.Table
import java.util.UUID

@Table("tbl_user")
class UserEntity(
    @get:JvmName("getIdentifier")
    override var id: UUID = UUID(0, 0),
    val accountId: String,
    val password: String,
    val name: String,
) : BaseUUIDEntity(id)
