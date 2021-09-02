package com.example.myapplication.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import com.example.myapplication.models.database.user.UserRepository
import com.example.myapplication.models.entities.User
import com.example.myapplication.ui.connect.ConnectViewModel
import java.lang.IllegalArgumentException

class MyProfileViewModel(userRepository: UserRepository) : ViewModel() {
    val candidates: LiveData<List<User>> = userRepository.allCandidates.asLiveData()
}

class MyProfileViewModelFactory(private val userRepository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyProfileViewModel::class.java)) {
            return MyProfileViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}