package com.example.dashboard.data.di

import android.content.Context
import androidx.room.Room
import com.example.dashboard.data.local.DashboardDatabase
import com.example.dashboard.data.local.StudentDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DashboardDbModule {

    @Provides
    fun providesDashboardDb(@ApplicationContext context: Context): DashboardDatabase {
        return Room.databaseBuilder(
            context,
            DashboardDatabase::class.java,
            "dashboard_db"
        ).build()
    }

    @Provides
    fun providesStudentDao(db: DashboardDatabase): StudentDao = db.getStudentDao()
}