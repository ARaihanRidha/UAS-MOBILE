package com.example.uasmobile

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AuthViewModelFactory(private val context: Context) : ViewModelProvider.Factory { //digunakan untuk membuat instance `ViewModel`
     override  fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) { //Memeriksa apakah `modelClass` dapat di-cast ke `AuthViewModel::class.java`.
            @Suppress("UNCHECKED_CAST") //Menekan peringatan casting yang tidak aman.
            return AuthViewModel(context) as T //Mengembalikan instance `AuthViewModel` yang telah dibuat.
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
