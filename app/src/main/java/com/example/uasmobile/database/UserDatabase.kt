package com.example.uasmobile.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.uasmobile.dao.UserDao
import com.example.uasmobile.model.User

@Database(entities = [User::class], version = 2, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {//implementasi Room Database dalam aplikasi Android.

    abstract fun userDao(): UserDao //Instance UserDao

    companion object { // Implementasi Singleton, digunakan untuk mendeklarasikan anggota yang dapat diakses tanpa harus membuat instance dari kelas tersebut.
        @Volatile//Annotation ini memastikan bahwa perubahan pada variabel `INSTANCE` segera terlihat oleh thread lain.
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase {//Fungsi ini mengembalikan instance singleton dari `UserDatabase
            return INSTANCE ?: synchronized(this) {// digunakan untuk memastikan bahwa hanya satu thread yang dapat mengakses kode di dalamnya pada satu waktu
                val instance = Room.databaseBuilder(
                    context.applicationContext,//Menggunakan context dari aplikasi untuk memastikan bahwa database tidak bocor (leak).
                    UserDatabase::class.java,
                    "user_database"
                )
                    .fallbackToDestructiveMigration()//Ketika data table diubah bisa membuat ulang database
                    .build()
                INSTANCE = instance//Menyimpan instance yang baru dibuat dalam variabel `INSTANCE`.
                instance
            }
        }
    }
}
