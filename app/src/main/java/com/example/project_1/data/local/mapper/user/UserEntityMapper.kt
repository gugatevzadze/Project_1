package com.example.project_1.data.local.mapper.user

import com.example.project_1.data.local.model.user.UserEntity
import com.example.project_1.domain.model.user.User

fun UserEntity.toDomain(): User {
    return User(
        id = id
    )
}

fun User.toData(): UserEntity {
    return UserEntity(
        id = id
    )
}