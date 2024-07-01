package com.example.uasmobile.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.uasmobile.model.User
@Dao
interface UserDao { //Untuk memodifikasi data dalam tabel user
    @Insert
    suspend fun insert(user: User)
    @Query("SELECT * FROM user_table WHERE username = :username AND password = :password")
    suspend fun login(username: String, password: String): User?
}