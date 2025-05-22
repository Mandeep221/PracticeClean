package com.example.dashboard.data.repo

import com.example.dashboard.data.NetworkResult
import com.example.dashboard.data.local.DashboardLocalDataSource
import com.example.dashboard.data.local.StudentEntity
import com.example.dashboard.data.model.toEntity
import com.example.dashboard.data.remote.DashboardRemoteDataSource
import com.example.dashboard.domain.DashboardRepo
import com.example.dashboard.domain.DomainResult
import com.example.dashboard.domain.model.UserEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DashboardRepoImpl @Inject constructor(
    private val remoteDataSource: DashboardRemoteDataSource,
    private val localDataSource: DashboardLocalDataSource
) : DashboardRepo {
    override suspend fun getDashboardData(): DomainResult<List<UserEntity>> {
        return when (val result = remoteDataSource.getDashboardData()) {
            is NetworkResult.Success -> DomainResult.Success(result.data.map { it.toEntity() })
            is NetworkResult.Error -> DomainResult.Failure(
                error = result.throwable ?: Exception("Something went wrong")
            )
        }
    }

    override fun getStudents(): Flow<List<UserEntity>> {
        return localDataSource.getStudents()
            .map { list -> list.map { UserEntity(it.id.toString(), it.name) } }
    }

    override suspend fun addStudent(name: String) {
        localDataSource.insertStudent(StudentEntity(name = name))
    }
}