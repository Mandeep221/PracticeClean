package com.example.dashboard.domain

import com.example.dashboard.domain.model.UserEntity
import javax.inject.Inject

class GetDashboardStuffUseCase @Inject constructor(
    private val repo: DashboardRepo
) {
    suspend fun execute(): DomainResult<List<UserEntity>> = repo.getDashboardData()
}

class AddStudentUseCase @Inject constructor(
    private val repo: DashboardRepo
) {
    suspend fun execute(name: String) = repo.addStudent(name)
}

class GetStudentsUseCase @Inject constructor(
    private val repo: DashboardRepo
) {
    fun execute() = repo.getStudents()
}