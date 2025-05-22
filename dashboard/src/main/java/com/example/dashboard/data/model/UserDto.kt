package com.example.dashboard.data.model

import com.example.dashboard.domain.model.UserEntity

data class UserDto(
    val id: String,
    val name: String
)

fun UserDto.toEntity() = UserEntity(
    id = id,
    name = name
)