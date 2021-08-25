package com.example.myapplication.models.database.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.models.entities.User
import com.example.myapplication.models.entities.UserType
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM JOB_PLATFORM_USERS")
    fun getAllCompanies(): Flow<List<User>>

    @Query("SELECT * FROM JOB_PLATFORM_USERS")
    fun getAllCandidates(): Flow<List<User>>
}