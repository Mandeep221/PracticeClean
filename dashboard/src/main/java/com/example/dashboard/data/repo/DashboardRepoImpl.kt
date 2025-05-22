package com.example.dashboard.data.repo

import com.example.dashboard.data.NetworkResult
import com.example.dashboard.data.model.toEntity
import com.example.dashboard.data.remote.DashboardRemoteDataSource
import com.example.dashboard.domain.DashboardRepo
import com.example.dashboard.domain.DomainResult
import com.example.dashboard.domain.model.UserEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DashboardRepoImpl @Inject constructor(
    private val dashboardRemoteDataSource: DashboardRemoteDataSource
) : DashboardRepo {
    override suspend fun getDashboardData(): DomainResult<List<UserEntity>> {
        return when (val result = dashboardRemoteDataSource.getDashboardData()) {
            is NetworkResult.Success -> DomainResult.Success(result.data.map { it.toEntity() })
            is NetworkResult.Error -> DomainResult.Failure(
                error = result.throwable ?: Exception("Something went wrong")
            )
        }
    }
}