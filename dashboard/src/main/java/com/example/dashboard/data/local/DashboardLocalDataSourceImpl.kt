package com.example.dashboard.data.local

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DashboardLocalDataSourceImpl @Inject constructor(
    private val studentDao: StudentDao
) : DashboardLocalDataSource {
    override fun getStudents(): Flow<List<StudentEntity>> = studentDao.getStudents()

    override suspend fun insertStudent(studentEntity: StudentEntity) = studentDao.insertStudent(studentEntity)
}