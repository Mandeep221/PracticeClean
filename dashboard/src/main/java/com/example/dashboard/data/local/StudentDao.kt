package com.example.dashboard.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {

    @Query("Select * from students")
    fun getStudents(): Flow<List<StudentEntity>>

    @Insert
    suspend fun insertStudent(studentEntity: StudentEntity)

}