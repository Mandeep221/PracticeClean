package com.example.dashboard.data.remote

import com.example.dashboard.data.model.UserDto
import retrofit2.Response
import retrofit2.http.GET

interface DashboardApi {

    @GET("users")
    suspend fun getUsers(): Response<List<UserDto>>
}