package com.example.myapplication.ui.data.model

import com.example.myapplication.models.entities.UserType

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
data class LoggedInUser(
    val userId: String,
    val displayName: String,
    val userType: Int
)