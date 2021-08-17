package com.example.myapplication.models.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.models.entities.User
import com.example.myapplication.models.entities.UserType

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM JOB_PLATFORM_USERS WHERE type = :userType")
    fun getAllCompanies(userType: UserType = UserType.Company):List<User>

    @Query("SELECT * FROM JOB_PLATFORM_USERS WHERE type = :userType")
    fun getAllCandidates(userType: UserType = UserType.Candidate): List<User>
}