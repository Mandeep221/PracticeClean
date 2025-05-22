package com.example.dashboard.data.di

import com.example.dashboard.data.local.DashboardLocalDataSource
import com.example.dashboard.data.local.DashboardLocalDataSourceImpl
import com.example.dashboard.data.remote.DashboardRemoteDataSource
import com.example.dashboard.data.remote.DashboardRemoteDataSourceImpl
import com.example.dashboard.data.repo.DashboardRepoImpl
import com.example.dashboard.domain.DashboardRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class DashboardBindingsModule {

    @Binds
    abstract fun bindsRemoteDataSource(
        dashboardRemoteDataSourceImpl: DashboardRemoteDataSourceImpl
    ): DashboardRemoteDataSource

    @Binds
    abstract fun bindsLocalDataSource(
        dashboardLocalDataSourceImpl: DashboardLocalDataSourceImpl
    ): DashboardLocalDataSource

    @Binds
    abstract fun bindsRepo(
        repoImpl: DashboardRepoImpl
    ): DashboardRepo
}