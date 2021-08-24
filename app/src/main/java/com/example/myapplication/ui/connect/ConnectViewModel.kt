package com.example.myapplication.ui.connect

import androidx.lifecycle.*
import com.example.myapplication.models.database.user.UserRepository
import com.example.myapplication.models.entities.User
import java.lang.IllegalArgumentException

class ConnectViewModel(private val userRepository: UserRepository) : ViewModel() {
    val candidates: LiveData<List<User>> = userRepository.allCandidates.asLiveData()
    val companies: LiveData<List<User>> = userRepository.allCompanies.asLiveData()
}

class ConnectViewModelFactory(private val userRepository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ConnectViewModel::class.java)) {
            return ConnectViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}