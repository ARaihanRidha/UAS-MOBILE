package com.example.uasmobile

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.uasmobile.dao.UserDao
import com.example.uasmobile.database.UserDatabase
import com.example.uasmobile.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthViewModel(context: Context) : ViewModel() {
    private val userDao: UserDao = UserDatabase.getDatabase(context).userDao()

    suspend fun login(username: String, password: String): User? {
        return withContext(Dispatchers.IO) {
            userDao.login(username, password)
        }
    }

    fun register(username: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            userDao.insert(User(username = username, password = password))
        }
    }
}
