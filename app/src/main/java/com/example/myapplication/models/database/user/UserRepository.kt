package com.example.myapplication.models.database.user

import androidx.annotation.WorkerThread
import com.example.myapplication.models.entities.User
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {
    @WorkerThread
    suspend fun insertUser(user: User) {
        userDao.insertUser(user)
    }

    val allCompanies: Flow<List<User>> = userDao.getAllCompanies()
    val allCandidates: Flow<List<User>> = userDao.getAllCandidates()
}