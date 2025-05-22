package com.example.dashboard.data.local

import kotlinx.coroutines.flow.Flow

interface DashboardLocalDataSource {
    fun getStudents(): Flow<List<StudentEntity>>
    suspend fun insertStudent(studentEntity: StudentEntity)
}