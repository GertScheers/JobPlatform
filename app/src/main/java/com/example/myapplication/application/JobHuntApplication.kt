package com.example.myapplication.application

import android.app.Application
import com.example.myapplication.models.database.JobHuntRoomDatabase
import com.example.myapplication.models.database.user.UserRepository

class JobHuntApplication : Application() {
    private val database by lazy { JobHuntRoomDatabase.getDatabase((this@JobHuntApplication)) }

    val userRepository by lazy { UserRepository(database.userDao()) }
}