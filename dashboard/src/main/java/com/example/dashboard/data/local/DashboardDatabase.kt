package com.example.dashboard.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [StudentEntity::class], version = 1)
abstract class DashboardDatabase: RoomDatabase() {
    abstract fun getStudentDao(): StudentDao
}
