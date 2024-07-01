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
    private val userDao: UserDao = UserDatabase.getDatabase(context).userDao() //Data Access Object yang digunakan untuk berinteraksi dengan tabel pengguna di database.

    suspend fun login(username: String, password: String): User? {
        return withContext(Dispatchers.IO) {//Menjalankan operasi database di thread I/O untuk menghindari blokir thread utama.
            userDao.login(username, password)
        }
    }

    fun register(username: String, password: String,fullName: String ,address: String, ) {
        viewModelScope.launch(Dispatchers.IO) { //Menjalankan operasi database di thread I/O untuk menghindari blokir thread utama.
            userDao.insert(User(username = username, password = password, fullName = fullName, address = address))
        }
    }
}
