package com.example.myapplication.models.database.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.models.entities.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM JOB_PLATFORM_USERS WHERE LOWER(email)=:email AND password=:password")
    fun login(email: String, password: String) : List<User>?

    @Query("SELECT * FROM JOB_PLATFORM_USERS WHERE type='0'")
    fun getAllCompanies(): Flow<List<User>>

    @Query("SELECT * FROM JOB_PLATFORM_USERS WHERE type='1'")
    fun getAllCandidates(): Flow<List<User>>

    @Query("SELECT * FROM JOB_PLATFORM_USERS WHERE id=:id")
    fun getUser(id: String) : User?
}