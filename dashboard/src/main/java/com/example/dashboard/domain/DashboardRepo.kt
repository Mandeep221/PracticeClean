package com.example.dashboard.domain

import com.example.dashboard.domain.model.UserEntity

interface DashboardRepo {
   suspend fun getDashboardData(): DomainResult<List<UserEntity>>
}