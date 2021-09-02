package com.example.myapplication.models.database.user

import android.util.Log
import androidx.annotation.WorkerThread
import com.example.myapplication.models.entities.User
import com.example.myapplication.ui.data.Result
import com.example.myapplication.ui.data.model.LoggedInUser
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {
    @WorkerThread
    suspend fun insertUser(user: User) {
        userDao.insertUser(user)
    }

    @WorkerThread
    fun doLogin(email: String, password: String): User? {
        return try {
            userDao.login(email, password)?.first()
        } catch (e: Exception){
            Log.e("ERR", e.printStackTrace().toString())
            null
        }
    }

    @WorkerThread
    fun getUser(id: String) : User? {
        return userDao.getUser(id)
    }

    val allCompanies: Flow<List<User>> = userDao.getAllCompanies()
    val allCandidates: Flow<List<User>> = userDao.getAllCandidates()


    // in-memory cache of the loggedInUser object
    var user: LoggedInUser? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        user = null
    }

    fun logout() {
        user = null
    }

    fun login(username: String, password: String): LoggedInUser? {
        // handle login
        Log.i("LOGIN", "before call")
        val result = doLogin(username, password)
        Log.i("LOGIN", "After call")

        if (result != null) {
            Log.i("LOGIN", "Result not null")
            val loggedInUser = LoggedInUser(result.id.toString(), result.displayName)
            setLoggedInUser(loggedInUser)
            Log.i("LOGIN", "Returning user")
            return loggedInUser
        }
        Log.i("LOGIN", "returning null")
        return null
    }

    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        this.user = loggedInUser
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}