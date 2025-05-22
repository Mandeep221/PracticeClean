package com.example.dashboard.domain

import com.example.dashboard.domain.model.UserEntity
import kotlinx.coroutines.flow.Flow

interface DashboardRepo {
   suspend fun getDashboardData(): DomainResult<List<UserEntity>>
   fun getStudents(): Flow<List<UserEntity>>
   suspend fun addStudent(name: String)
}