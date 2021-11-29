package com.example.myapplication.models.database.user

import android.util.Log
import androidx.annotation.WorkerThread
import com.example.myapplication.models.entities.User
import com.example.myapplication.ui.data.model.LoggedInUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class UserRepository(private val userDao: UserDao) {
    @WorkerThread
    suspend fun insertUser(user: User) {
        userDao.insertUser(user)
    }

    @WorkerThread
    fun doLogin(email: String, password: String): User? {
        return try {
            userDao.login(email.lowercase(), password)?.first()
        } catch (e: Exception){
            Log.e("ERR", e.printStackTrace().toString())
            null
        }
    }

    @WorkerThread
    fun getUser(id: Int) : User? {
        return userDao.getUser(id)
    }

    val allCompanies: Flow<List<User>> = userDao.getAllCompanies()
    val allCandidates: Flow<List<User>> = userDao.getAllCandidates()

    private val _currentUser = MutableStateFlow<LoggedInUser?>(null)
    val currentUserFlow: StateFlow<LoggedInUser?> = _currentUser

    val isLoggedIn: Boolean
        get() = _currentUser.value != null


    fun logout() {
        _currentUser.value = null
    }

    fun login(username: String, password: String): LoggedInUser? {
        // handle login
        Log.i("LOGIN", "before call")
        val result = doLogin(username, password)
        Log.i("LOGIN", "After call")

        if (result != null) {
            Log.i("LOGIN", "Result not null")
            val loggedInUser = LoggedInUser(result.id, result.displayName, result.type)
            setLoggedInUser(loggedInUser)
            Log.i("LOGIN", "Returning user")
            return loggedInUser
        }
        Log.i("LOGIN", "returning null")
        return null
    }

    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        _currentUser.value = loggedInUser
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}