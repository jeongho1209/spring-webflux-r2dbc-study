package com.example.springwebflux.domain.user.domain

import com.example.springwebflux.common.entity.BaseUUIDEntity
import com.example.springwebflux.common.error.ForbiddenException
import org.springframework.data.relational.core.mapping.Table
import java.util.UUID

@Table("tbl_user")
class UserEntity(
    @get:JvmName("getIdentifier")
    override var id: UUID = UUID(0, 0),
    val accountId: String,
    val password: String,
    val name: String,
) : BaseUUIDEntity(id) {
    fun validateUserId(targetUserId: UUID, currentUserId: UUID) {
        if (targetUserId != currentUserId) {
            throw ForbiddenException(ForbiddenException.FORBIDDEN_EXCEPTION)
        }
    }
}
