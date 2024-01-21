package com.example.project_1.presentation.mapper.user

import com.example.project_1.domain.model.user.User
import com.example.project_1.presentation.model.user.UserModel

fun UserModel.toDomain(): User {
    return User(
        id = id
    )
}

fun User.toPresentation(): UserModel {
    return UserModel(
        id = id
    )
}