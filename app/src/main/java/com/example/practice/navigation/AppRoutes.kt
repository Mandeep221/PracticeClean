package com.example.practice.navigation

import kotlinx.serialization.Serializable

@Serializable
object Dashboard

@Serializable
data class Profile(
    val name: String? = null
)

@Serializable
object ScoreLogic