package com.example.myapplication.application

import android.app.Application
import com.example.myapplication.models.database.JobHuntRoomDatabase
import com.example.myapplication.models.database.user.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class JobHuntApplication : Application() {
    private val database by lazy {
        runBlocking {
            JobHuntRoomDatabase.getDatabase((this@JobHuntApplication))
        }
    }

    val userRepository by lazy { UserRepository(database.userDao()) }
}