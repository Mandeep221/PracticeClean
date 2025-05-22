package com.example.dashboard.data.remote

import com.example.dashboard.data.NetworkResult
import com.example.dashboard.data.model.UserDto

interface DashboardRemoteDataSource {
    suspend fun getDashboardData(): NetworkResult<List<UserDto>>
}