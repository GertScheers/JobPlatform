package com.example.myapplication.ui.profile

import androidx.annotation.WorkerThread
import androidx.lifecycle.*
import com.example.myapplication.models.database.user.UserRepository
import com.example.myapplication.models.entities.User
import com.example.myapplication.ui.connect.ConnectViewModel
import com.example.myapplication.ui.data.model.LoggedInUser
import com.example.myapplication.ui.ui.login.LoginResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.DisposableHandle
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.IllegalArgumentException

class MyProfileViewModel(private val userRepository: UserRepository) : ViewModel() {
    val user: LoggedInUser? = userRepository.currentUserFlow.value

    private val _fullUser = MutableLiveData<User>()
    var fullUser: LiveData<User> = _fullUser

    fun getUser(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = userRepository.getUser(id)
            withContext(Dispatchers.Main) {
                if (result != null)
                    _fullUser.value = result!!
            }
        }
    }
}

class MyProfileViewModelFactory(private val userRepository: UserRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyProfileViewModel::class.java)) {
            return MyProfileViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}