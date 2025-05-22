package com.example.dashboard.data.di

import com.example.core.data.network.di.PlaceholderJsonRetrofit
import com.example.dashboard.data.remote.DashboardApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object DashboardNetworkModule {


    @Provides
    fun providesDashboardApi(@PlaceholderJsonRetrofit retrofit: Retrofit): DashboardApi {
        return retrofit.create(DashboardApi::class.java)
    }
}