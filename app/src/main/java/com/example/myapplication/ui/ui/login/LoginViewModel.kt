package com.example.myapplication.ui.ui.login

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.*
import com.example.myapplication.ui.data.Result

import com.example.myapplication.R
import com.example.myapplication.models.database.user.UserRepository
import com.example.myapplication.models.entities.User
import com.example.myapplication.ui.connect.ConnectViewModel
import com.example.myapplication.ui.data.model.LoggedInUser
import kotlinx.coroutines.*
import java.lang.IllegalArgumentException

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    suspend fun login(username: String, password: String): Boolean {
        // can be launched in a separate asynchronous job
        var user: LoggedInUser?

        Log.i("VM", "before coroutine")
        withContext(Dispatchers.IO) {
            user = userRepository.login(username, password)
            Log.i("VM", "User returned: " + user?.displayName)
        }

        if (user != null) {
            Log.i("VM", "Assigning new LoginResult")
            _loginResult.value =
                LoginResult(LoggedInUserView(user!!.displayName))
            Log.i("VM", "Assigned new result")
        } else {
            _loginResult.value = LoginResult(error = R.string.login_failed)
        }
        Log.i("VM", "end of coroutine")

        return _loginResult.value?.success != null
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains("@")) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 3
    }
}

class LoginViewModelFactory(private val userRepository: UserRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}