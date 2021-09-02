package com.example.myapplication.ui.register

import androidx.lifecycle.*
import com.example.myapplication.models.database.user.UserRepository
import com.example.myapplication.models.entities.User
import kotlinx.coroutines.launch

class RegisterViewModel(private val repository: UserRepository) : ViewModel() {
    fun insert(user: User) = viewModelScope.launch {
        repository.insertUser(user)
    }
}

class RegisterViewModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}