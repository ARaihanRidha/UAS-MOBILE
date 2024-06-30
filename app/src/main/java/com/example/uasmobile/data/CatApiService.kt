package com.example.uasmobile.api

import com.example.uasmobile.data.Breed
import com.example.uasmobile.data.Cat
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

const val BASE_URL = "https://api.thecatapi.com/v1/"
const val API_KEY = "live_ZHh4vOsMy4Qt7vrjNmpPUY7PYidXS8SBLdI5HBvjO3q5KKPixXNJ2Xz0ejDH2j0O"

data class CatResponse(
    val id: String = "",
    val url: String = "",
    val width: Int = 0,
    val height: Int = 0,
    val breeds: List<Breed> = emptyList()
) {
    
}

interface CatApiService {
    @Headers("x-api-key: $API_KEY")
    @GET("images/search")
    suspend fun getCatImages(
        @Query("limit") limit: Int = 1,
        @Query("has_breeds") hasBreeds: Boolean = true): List<CatResponse>
    suspend fun getCatDetail(
        @Path("id") id: String,
        @Query("api_key") apiKey: String
    ): Cat
}

object CatApi {
    val retrofitService: CatApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CatApiService::class.java)
    }
}
