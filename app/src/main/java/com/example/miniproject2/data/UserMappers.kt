package com.example.miniproject2.data

import com.example.miniproject2.data.local.UserEntity
import com.example.miniproject2.data.remote.UserApiModel

// Converts a single API model to the Room entity
fun UserApiModel.toEntity(): UserEntity =
    UserEntity(
        id = id,
        name = name,
        email = email,
        phone = phone
    )

// Converts a list of API models to a list of the Room entities
fun List<UserApiModel>.toEntityList(): List<UserEntity> =
    map { it.toEntity() }
