package com.example.uasmobile.api

import com.example.uasmobile.data.Cat
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

const val BASE_URL = "https://api.thecatapi.com/v1/"
const val API_KEY = "live_ZHh4vOsMy4Qt7vrjNmpPUY7PYidXS8SBLdI5HBvjO3q5KKPixXNJ2Xz0ejDH2j0O"

interface CatApiService { // Fitur Fetch API
    @Headers("x-api-key: $API_KEY") //Menambah Header API key ke method dibawahnya
    @GET("images/search") //endpoint
    suspend fun getCatImages(
        @Query("limit") limit: Int = 1,
        @Query("has_breeds") hasBreeds: Boolean = true): List<Cat>
    suspend fun getCatDetail( //suspend untuk mengambil detail kucing berdasarkan ID.
        @Path("id") id: String,
        @Query("api_key") apiKey: String
    ): Cat
}

object CatApi { //Object Singleton menyediakan instance catAPIService retrofit
    val retrofitService: CatApiService by lazy {
        Retrofit.Builder() //Membangun instance Retrofit
            .baseUrl(BASE_URL) //Mengatur URL dasar untuk semua permintaan yang dibuat oleh Retrofit.
            .addConverterFactory(GsonConverterFactory.create()) //Menambahkan converter factory
            .build() //Membangun instance Retrofit.
            .create(CatApiService::class.java) // untuk mengkonversi JSON ke objek Kotlin.
    }
}
