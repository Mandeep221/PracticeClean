package com.example.dashboard.ui.model

import com.example.dashboard.domain.model.UserEntity

data class User(
    val id: String,
    val name: String
)

fun UserEntity.toUiModel() = User(
    id = id,
    name = name
)