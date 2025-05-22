package com.example.dashboard.data.remote

import com.example.dashboard.data.NetworkResult
import com.example.dashboard.data.model.UserDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DashboardRemoteDataSourceImpl @Inject constructor(
    private val dashboardApi: DashboardApi
) : DashboardRemoteDataSource {
    override suspend fun getDashboardData(): NetworkResult<List<UserDto>> {
        val result = dashboardApi.getUsers()

        return try {
            if (result.isSuccessful) {
                // Success and error for 2xx
                val body = result.body()
                if (body != null) {
                    NetworkResult.Success(body)
                } else {
                    NetworkResult.Error(message = result.message())
                }
            } else {
                // all other cases 4xx, 5xx
                NetworkResult.Error(message = result.message(), code = result.code())
            }
        } catch (e: Exception) {
            // All other exceptions
            // IOException - no internet,
            // SocketTimeoutException
            // JsonParseException
            NetworkResult.Error(message = result.message())
        }
    }
}